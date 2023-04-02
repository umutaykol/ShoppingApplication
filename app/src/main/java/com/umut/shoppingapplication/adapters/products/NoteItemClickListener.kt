package com.umut.shoppingapplication.adapters.products

import android.view.View
import com.umut.shoppingapplication.models.Product

interface ProductItemClickListener {

    fun addProductClicked(view: View, product: Product, position: Int)

    fun subProductClicked(view: View, product: Product, position: Int)

}