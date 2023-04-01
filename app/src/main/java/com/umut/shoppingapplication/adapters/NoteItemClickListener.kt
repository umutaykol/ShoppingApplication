package com.umut.shoppingapplication.adapters

import android.view.View
import com.umut.shoppingapplication.models.Product

interface NoteItemClickListener {

    fun addProductClicked(view: View, product: Product, position: Int)

    fun subProductClicked(view: View, product: Product, position: Int)

}