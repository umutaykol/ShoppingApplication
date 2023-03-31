package com.umut.shoppingapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.umut.shoppingapplication.models.Product

@Database(entities = [Product::class], version = 1)
abstract class ProductsDB : RoomDatabase() {

    abstract fun EntityDao(): ProductsDao

    companion object {

        @Volatile
        private var INSTANCE: ProductsDB? = null

        fun getDatabase(context: Context): ProductsDB {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext, ProductsDB::class.java, "ProductsDatabase"
                    ).fallbackToDestructiveMigration().build()
                }
            }

            return INSTANCE!!
        }
    }

}