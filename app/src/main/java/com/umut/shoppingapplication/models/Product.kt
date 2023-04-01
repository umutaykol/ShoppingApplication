package com.umut.shoppingapplication.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "product_table")
data class Product(
    @PrimaryKey var id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "name") var productName: String = "",
    @ColumnInfo(name = "price") var productPrice: Float = 0F,
    @ColumnInfo(name = "count") var productCount: Int = 0,
) : Parcelable
