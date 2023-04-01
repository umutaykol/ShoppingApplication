package com.umut.shoppingapplication.repository.orders

import com.umut.shoppingapplication.database.orders.OrdersDao
import com.umut.shoppingapplication.models.Order
import javax.inject.Inject

class OrdersRepositoryImpl @Inject constructor(
    var ordersDao: OrdersDao
) : OrdersRepository {

    override suspend fun insertAll(vararg orders: Order): List<Long> = ordersDao.insertAll(*orders)

    override suspend fun insertOrder(order: Order) = ordersDao.insertOrder(order)

    override suspend fun updateOrder(order: Order) = ordersDao.updateOrder(order)

    override suspend fun deleteAll(vararg orders: Order) = ordersDao.deleteAll(*orders)

    override suspend fun deleteOrder(order: Order) = ordersDao.deleteOrder(order)

    override suspend fun getAllOrders(): MutableList<Order> = ordersDao.getAllOrders()

    override suspend fun deleteAllOrders() = ordersDao.deleteAllOrders()

}