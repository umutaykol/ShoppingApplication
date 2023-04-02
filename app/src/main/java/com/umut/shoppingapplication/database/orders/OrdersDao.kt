package com.umut.shoppingapplication.database.orders

import androidx.room.*
import com.umut.shoppingapplication.models.Order

@Dao
interface OrdersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg orders: Order): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: Order)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateOrder(order: Order)

    @Delete
    suspend fun deleteAll(vararg notesEntities: Order)

    @Delete
    suspend fun deleteOrder(order: Order)

    @Query("Select * from order_table")
    suspend fun getAllOrders(): MutableList<Order>

    @Query("Delete from order_table")
    suspend fun deleteAllOrders()

}