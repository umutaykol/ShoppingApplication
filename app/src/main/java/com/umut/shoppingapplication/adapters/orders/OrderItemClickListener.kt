package com.umut.shoppingapplication.adapters.orders

import android.view.View
import com.umut.shoppingapplication.models.Order

interface OrderItemClickListener {

    fun deleteOrderClicked(view: View, order: Order, position: Int)

}