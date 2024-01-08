package com.personal.unihub.repository

import com.personal.unihub.model.ProductItem

class SortHelper {

    fun sortProducts(type: SortType, productsList: List<ProductItem>): List<ProductItem> {
        return when (type) {
            SortType.Alphabetically -> productsList.sortAlphabetically()
            SortType.PriceAsc -> productsList.sortBasedOnPriceAsc()
            SortType.PriceDesc -> productsList.sortBasedOnPriceDesc()
        }
    }

    private fun List<ProductItem>.sortAlphabetically(): List<ProductItem> {
        return sortedBy { it.title }
    }

    private fun List<ProductItem>.sortBasedOnPriceAsc(): List<ProductItem> {
        return sortedBy { it.price }
    }

    private fun List<ProductItem>.sortBasedOnPriceDesc(): List<ProductItem> {
        return sortedByDescending { it.price }
    }

}