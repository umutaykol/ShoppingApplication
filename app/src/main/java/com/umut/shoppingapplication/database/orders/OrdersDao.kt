package com.umut.shoppingapplication.database.orders

import androidx.room.*
import com.umut.shoppingapplication.models.Order

@Dao
interface OrdersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg notes: Order): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(note: Order)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateOrder(note: Order)

    @Delete
    suspend fun deleteAll(vararg notesEntities: Order)

    @Delete
    suspend fun deleteOrder(note: Order)

    @Query("Select * from order_table")
    suspend fun getAllOrders(): MutableList<Order>

    @Query("Delete from order_table")
    suspend fun deleteAllOrders()

}