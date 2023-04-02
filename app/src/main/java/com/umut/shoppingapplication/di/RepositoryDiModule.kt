package com.umut.shoppingapplication.di

import com.umut.shoppingapplication.repository.orders.OrdersRepository
import com.umut.shoppingapplication.repository.orders.OrdersRepositoryImpl
import com.umut.shoppingapplication.repository.products.ProductsRepository
import com.umut.shoppingapplication.repository.products.ProductsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class RepositoryDiModule {

    @Binds
    abstract fun provideProductsRepository(repo: ProductsRepositoryImpl): ProductsRepository

    @Binds
    abstract fun provideOrdersRepository(repo: OrdersRepositoryImpl): OrdersRepository

}