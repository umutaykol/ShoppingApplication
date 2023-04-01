package com.umut.shoppingapplication.adapters

import android.view.View
import com.umut.shoppingapplication.models.Product

interface NoteItemClickListener {

    fun cardLongClick(view: View, note: Product, position: Int): Boolean

    fun cardOnClick(view: View, note: Product, position: Int)

}