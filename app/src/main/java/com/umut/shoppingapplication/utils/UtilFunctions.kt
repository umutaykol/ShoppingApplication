package com.umut.shoppingapplication.utils

import android.content.Context
import android.widget.Toast
import com.umut.shoppingapplication.utils.Constants.white_space


fun showLongToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun showShortToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun String.maskedCardNumber(): String {
    if (length >= 16) {
        return filter { it != white_space }.replaceRange(6..11, "******")
    }
    return "-"
}