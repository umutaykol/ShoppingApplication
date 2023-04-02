package com.umut.shoppingapplication.adapters.orders

import android.view.View
import com.umut.shoppingapplication.models.Product

interface OrderItemClickListener {

    fun deleteOrderClicked(view: View, product: Product, position: Int)

    fun orderInformationClicked(view: View, product: Product, position: Int)

}