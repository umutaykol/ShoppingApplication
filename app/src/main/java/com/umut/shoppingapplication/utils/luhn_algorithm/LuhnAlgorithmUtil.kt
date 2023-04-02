package com.umut.shoppingapplication.utils.luhn_algorithm

fun String.checkLuhnAlgorithm(): Boolean {
    val creditCardNumber = this.filter { it != ' ' }

    if (creditCardNumber.length != 16) return false

    var checksum = 0

    for (i in creditCardNumber.length - 1 downTo 0 step 2) {
        checksum += creditCardNumber[i] - '0'
    }

    for (i in creditCardNumber.length - 2 downTo 0 step 2) {
        val n: Int = (creditCardNumber[i] - '0') * 2
        checksum += if (n > 9) n - 9 else n
    }

    return checksum % 10 == 0
}