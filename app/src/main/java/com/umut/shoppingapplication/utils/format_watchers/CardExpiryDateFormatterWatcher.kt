package com.umut.shoppingapplication.utils.format_watchers

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher

/**
 * Formats the watched EditText to a Expiry Date
 *
 * Expected Expiry Date -> MM/yy
 */
class CardExpiryDateFormatterWatcher : TextWatcher {

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    override fun afterTextChanged(s: Editable) {
        // Remove spacing char
        if (s.isNotEmpty() && s.length % 3 == 0) {
            val c = s[s.length - 1]
            if (slash == c) {
                s.delete(s.length - 1, s.length)
            }
        }
        // Insert char where needed.
        if (s.isNotEmpty() && s.length % 3 == 0) {
            val c = s[s.length - 1]
            // Only if its a digit where there should be a space we insert a space
            if (Character.isDigit(c) && TextUtils.split(s.toString(), slash.toString()).size <= 3) {
                s.insert(s.length - 1, slash.toString())
            }
        }
    }

    companion object {

        // Change this to what you want... ' ', '-' etc..
        private const val slash = '/'
    }
}