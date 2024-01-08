package com.personal.unihub.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.personal.unihub.model.ProductItem
import com.personal.unihub.ui.ProductDetails

class ProductActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        val title = intent.getStringExtra(KEY_TITLE)!!
        val price = intent.getDoubleExtra(KEY_PRICE, 0.00)
        val category = intent.getStringExtra(KEY_CATEGORY)!!
        val image = intent.getIntExtra(KEY_IMAGE, 0)
        val productItem = ProductItem(title, price, category, image)
        setContent { ProductDetails(productItem) }
    }

    companion object {
        const val KEY_TITLE = "title"
        const val KEY_PRICE = "price"
        const val KEY_IMAGE = "image"
        const val KEY_CATEGORY = "category"
    }
}
