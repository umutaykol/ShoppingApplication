package com.umut.shoppingapplication.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "order_table")
data class Order(
    @PrimaryKey var id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "credit_card_number") var creditCardNumber: String = "",
    @ColumnInfo(name = "amount") var orderAmount: Float = 0F,
    @ColumnInfo(name = "date") var orderDate: String = "",
) : Parcelable
