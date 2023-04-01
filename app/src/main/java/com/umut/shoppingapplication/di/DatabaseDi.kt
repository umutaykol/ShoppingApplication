package com.umut.shoppingapplication.di

import android.content.Context
import com.umut.shoppingapplication.database.orders.OrdersDB
import com.umut.shoppingapplication.database.orders.OrdersDao
import com.umut.shoppingapplication.database.products.ProductsDB
import com.umut.shoppingapplication.database.products.ProductsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseDi {

    @Singleton
    @Provides
    fun provideProductsDatabase(@ApplicationContext context: Context): ProductsDao {
        return ProductsDB.getDatabase(context).EntityDao()
    }

    @Singleton
    @Provides
    fun proviceOrdersDatabase(@ApplicationContext context: Context): OrdersDao {
        return OrdersDB.getDatabase(context).EntityDao()
    }

}