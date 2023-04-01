package com.umut.shoppingapplication.repository.orders

import com.umut.shoppingapplication.models.Order

interface OrdersRepository {

    suspend fun insertAll(vararg orders: Order) : List<Long>

    suspend fun insertOrder(order: Order)

    suspend fun updateOrder(order: Order)

    suspend fun deleteAll(vararg orders: Order)

    suspend fun deleteOrder(order: Order)

    suspend fun getAllOrders(): MutableList<Order>

    suspend fun deleteAllOrders()

}