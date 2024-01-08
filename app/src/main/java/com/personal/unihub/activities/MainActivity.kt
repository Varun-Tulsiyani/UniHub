package com.personal.unihub.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.view.MenuCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.personal.unihub.R
import com.personal.unihub.activities.ToDoActivity.Companion.KEY_CATEGORY
import com.personal.unihub.activities.ToDoActivity.Companion.KEY_CONTENT
import com.personal.unihub.activities.ToDoActivity.Companion.KEY_TIMESTAMP
import com.personal.unihub.activities.ToDoActivity.Companion.KEY_TITLE
import com.personal.unihub.databases.ProductsWarehouse
import com.personal.unihub.databinding.ActivityMainBinding
import com.personal.unihub.model.ProductItem
import com.personal.unihub.model.Products
import com.personal.unihub.model.Task
import com.personal.unihub.model.TaskList
import com.personal.unihub.repository.FilterHelper
import com.personal.unihub.repository.FilterType
import com.personal.unihub.repository.SortHelper
import com.personal.unihub.repository.SortType
import com.personal.unihub.ui.todo.TaskWarehouse.tasksList
import com.personal.unihub.ui.todo.ToDoGrid
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class MainActivity : AppCompatActivity() {

    private val tasksState: MutableStateFlow<TaskList> =
        MutableStateFlow(TaskList(tasksList))

    private val productsState: MutableStateFlow<Products> =
        MutableStateFlow(Products(ProductsWarehouse.productsList))

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent { InitUI() }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController: NavController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_timetable, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    @Composable
    fun InitUI() {
        val tasks by tasksState.collectAsState()
        ToDoGrid(startToDoActivity = this::startToDoActivity)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun startToDoActivity(context: Context, task: Task) {
        val intent = Intent(context, ToDoActivity::class.java).apply {
            intent.putExtra("title", KEY_TITLE)
            intent.putExtra("content", KEY_CONTENT)
            intent.putExtra("category", KEY_CATEGORY)
            intent.putExtra("timestamp", KEY_TIMESTAMP)
        }
        context.startActivity(intent)
    }

    private fun startProductActivity(context: Context, productItem: ProductItem) {
        val intent = Intent(context, ProductActivity::class.java).apply {
            intent.putExtra("title", ProductActivity.KEY_TITLE)
            intent.putExtra("price", ProductActivity.KEY_PRICE)
            intent.putExtra("image", ProductActivity.KEY_IMAGE)
            intent.putExtra("category", ProductActivity.KEY_CATEGORY)
        }
        context.startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.products_menu, menu)
        MenuCompat.setGroupDividerEnabled(menu!!, true)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.groupId == R.id.sorting) {
            val type = when (item.itemId) {
                R.id.sort_a_z -> SortType.Alphabetically
                R.id.sort_price_asc -> SortType.PriceAsc
                R.id.sort_price_desc -> SortType.PriceDesc
                else -> SortType.Alphabetically
            }
            productsState.update {
                Products(
                    SortHelper().sortProducts(
                        type,
                        ProductsWarehouse.productsList
                    )
                )
            }
        } else if (item.groupId == R.id.filter) {
            val type = when (item.itemId) {
                R.id.filter_all -> FilterType.All
                R.id.filter_drinks -> FilterType.Drinks
                R.id.filter_food -> FilterType.Food
                R.id.filter_dessert -> FilterType.Dessert
                else -> FilterType.All
            }
            productsState.update {
                Products(
                    FilterHelper().filterProducts(
                        type,
                        ProductsWarehouse.productsList
                    )
                )
            }
        }
        return true
    }
}