package com.umut.shoppingapplication.database.products

import androidx.room.*
import com.umut.shoppingapplication.models.Product

@Dao
interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg notes: Product): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(note: Product)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProduct(note: Product)

    @Delete
    suspend fun deleteAll(vararg notesEntities: Product)

    @Delete
    suspend fun deleteProduct(note: Product)

    @Query("Select * from product_table")
    suspend fun getAllProducts(): MutableList<Product>

    @Query("Delete from product_table")
    suspend fun deleteAllProducts()

}