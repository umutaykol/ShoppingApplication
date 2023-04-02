package com.umut.shoppingapplication.utils.format_watchers

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import com.umut.shoppingapplication.utils.Constants.white_space

/**
 * Formats the watched EditText to a credit card number
 */
class FourDigitCardFormatterWatcher : TextWatcher {

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    override fun afterTextChanged(s: Editable) {
        // Remove spacing char
        if (s.isNotEmpty() && s.length % 5 == 0) {
            val c = s[s.length - 1]
            if (white_space == c) {
                s.delete(s.length - 1, s.length)
            }
        }
        // Insert char where needed.
        if (s.isNotEmpty() && s.length % 5 == 0) {
            val c = s[s.length - 1]
            // Only if its a digit where there should be a space we insert a white_space
            if (Character.isDigit(c) && TextUtils.split(s.toString(), white_space.toString()).size <= 3) {
                s.insert(s.length - 1, white_space.toString())
            }
        }
    }
}