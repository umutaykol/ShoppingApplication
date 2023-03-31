package com.umut.shoppingapplication.di

import com.umut.shoppingapplication.repository.ProductsRepository
import com.umut.shoppingapplication.repository.ProductsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class) // Scope our dependencies
@Module
abstract class ProductModule {

    // To be read as — When someone asks for DataRepository, create a DataRepoImpl and return it.
    @Binds
    abstract fun getProfileSource(repo: ProductsRepositoryImpl): ProductsRepository
}