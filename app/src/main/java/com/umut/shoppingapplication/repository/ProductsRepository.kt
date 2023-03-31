package com.umut.shoppingapplication.repository

import com.umut.shoppingapplication.models.Product

interface ProductsRepository {

    suspend fun insertAll(vararg notes: Product) : List<Long>

    suspend fun insertProduct(Product: Product)

    suspend fun updateProduct(Product: Product)

    suspend fun deleteAll(vararg Products: Product)

    suspend fun deleteProduct(Product: Product)

    suspend fun getAllProducts(): MutableList<Product>

    suspend fun deleteAllProducts()

}