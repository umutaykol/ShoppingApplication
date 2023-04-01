package com.umut.shoppingapplication.database.orders

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.umut.shoppingapplication.models.Order

@Database(entities = [Order::class], version = 1)
abstract class OrdersDB : RoomDatabase() {

    abstract fun EntityDao(): OrdersDao

    companion object {

        @Volatile
        private var INSTANCE: OrdersDB? = null

        fun getDatabase(context: Context): OrdersDB {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext, OrdersDB::class.java, "OrdersDatabase"
                    ).fallbackToDestructiveMigration().build()
                }
            }

            return INSTANCE!!
        }
    }

}