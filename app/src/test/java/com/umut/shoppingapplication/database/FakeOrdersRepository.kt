package com.umut.shoppingapplication.database

import com.umut.shoppingapplication.models.Order
import com.umut.shoppingapplication.models.Product
import com.umut.shoppingapplication.repository.orders.OrdersRepository
import com.umut.shoppingapplication.repository.products.ProductsRepository

class FakeOrdersRepository : OrdersRepository {

    private var orders = mutableListOf<Order>()

    override suspend fun insertAll(vararg orders: Order): List<Long> {
        this.orders.addAll(orders)
        return listOf(orders.size.toLong())
    }

    override suspend fun insertOrder(order: Order) {
        orders.add(order)
    }

    override suspend fun updateOrder(order: Order) {
        val updatedOrders = orders.map { if (it.id == order.id) order else it } as MutableList
        orders = updatedOrders
    }

    override suspend fun deleteAll(vararg orders: Order) {
        orders.forEach {  product ->
            val updatedProducts = orders.filter { it.id != product.id } as MutableList
            this.orders = updatedProducts
        }
    }

    override suspend fun deleteOrder(order: Order) {
        val updatedProducts = orders.filter { it.id != order.id } as MutableList
        orders = updatedProducts
    }

    override suspend fun getAllOrders(): MutableList<Order> = orders

    override suspend fun deleteAllOrders() {
        orders = mutableListOf()
    }
}