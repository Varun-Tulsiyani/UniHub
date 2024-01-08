package com.personal.unihub.ui

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.personal.unihub.model.ProductItem
import com.personal.unihub.model.Products

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductsGrid(products: Products, startProductActivity: (Context, ProductItem) -> Unit) {
    val context = LocalContext.current
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 2),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(
            count = products.items.size,
            itemContent = { index: Int ->
                val productItem = products.items[index]
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = productItem.image),
                        contentDescription = productItem.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.clickable {
                            startProductActivity(context, productItem)
                        }
                    )
                    Text(text = productItem.title)
                }
            }
        )
    }
}
