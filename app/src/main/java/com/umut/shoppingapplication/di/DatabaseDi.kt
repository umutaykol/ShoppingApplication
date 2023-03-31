package com.umut.shoppingapplication.di

import android.content.Context
import com.umut.shoppingapplication.database.ProductsDB
import com.umut.shoppingapplication.database.ProductsDao
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

}