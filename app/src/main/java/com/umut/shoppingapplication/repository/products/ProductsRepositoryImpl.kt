package com.umut.shoppingapplication.repository.products

import com.umut.shoppingapplication.database.products.ProductsDao
import com.umut.shoppingapplication.models.Product
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    var productsDao: ProductsDao
) : ProductsRepository {

    override suspend fun insertAll(vararg products: Product): List<Long> = productsDao.insertAll(*products)

    override suspend fun insertProduct(Product: Product) = productsDao.insertProduct(Product)

    override suspend fun updateProduct(Product: Product) = productsDao.updateProduct(Product)

    override suspend fun deleteAll(vararg Products: Product) = productsDao.deleteAll(*Products)

    override suspend fun deleteProduct(Product: Product) = productsDao.deleteProduct(Product)

    override suspend fun getAllProducts(): MutableList<Product> = productsDao.getAllProducts()

    override suspend fun deleteAllProducts() = productsDao.deleteAllProducts()

}