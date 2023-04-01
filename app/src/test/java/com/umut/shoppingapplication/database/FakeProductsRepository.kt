package com.umut.shoppingapplication.database

import com.umut.shoppingapplication.models.Product
import com.umut.shoppingapplication.repository.products.ProductsRepository

class FakeProductsRepository : ProductsRepository {

    private var products = mutableListOf<Product>()

    override suspend fun insertAll(vararg products: Product): List<Long> {
        this.products.addAll(products)
        return listOf(products.size as Long)
    }

    override suspend fun insertProduct(product: Product) {
        products.add(product)
    }

    override suspend fun updateProduct(product: Product) {
        val updatedProducts = products.map { if (it.id == product.id) product else it } as MutableList
        products = updatedProducts
    }

    override suspend fun deleteAll(vararg products: Product) {
        products.forEach {  product ->
            val updatedProducts = products.filter { it.id != product.id } as MutableList
            this.products = updatedProducts
        }
    }

    override suspend fun deleteProduct(product: Product) {
        val updatedProducts = products.filter { it.id != product.id } as MutableList
        products = updatedProducts
    }

    override suspend fun getAllProducts(): MutableList<Product> = products

    override suspend fun deleteAllProducts() {
        products = mutableListOf()
    }
}