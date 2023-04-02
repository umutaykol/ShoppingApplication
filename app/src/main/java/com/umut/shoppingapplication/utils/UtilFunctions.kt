package com.umut.shoppingapplication.utils

import android.content.Context
import android.widget.Toast


fun showLongToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun showShortToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun String.maskedCardNumber(): String {
    return this.replaceRange(6..11, "******")
}