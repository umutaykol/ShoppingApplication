package com.umut.shoppingapplication.repository.products

import com.umut.shoppingapplication.models.Product

interface ProductsRepository {

    suspend fun insertAll(vararg products: Product) : List<Long>

    suspend fun insertProduct(product: Product)

    suspend fun updateProduct(product: Product)

    suspend fun deleteAll(vararg products: Product)

    suspend fun deleteProduct(Product: Product)

    suspend fun getAllProducts(): MutableList<Product>

    suspend fun deleteAllProducts()

}