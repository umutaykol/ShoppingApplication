package com.umut.shoppingapplication.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.umut.shoppingapplication.R
import com.umut.shoppingapplication.models.Product

@BindingAdapter("app:setProductImage")
fun setProductImage(view: View, product: Product) {
    (view as ImageView).setImageResource(
        when (product.productName) {
            "Kitap" -> R.drawable.book
            "Kalem" -> R.drawable.pen
            "Silgi" -> R.drawable.eraser
            "Kağıt" -> R.drawable.paper
            "Defter" -> R.drawable.notebook

            else -> {
                R.drawable.ic_launcher_background
            }
        }
    )
}

@BindingAdapter("app:setAsTextofProductPrice")
fun setAsTextofProductPrice(view: View, product: Product) {
    (view as TextView).text = "Price: ${product.productPrice}"
}