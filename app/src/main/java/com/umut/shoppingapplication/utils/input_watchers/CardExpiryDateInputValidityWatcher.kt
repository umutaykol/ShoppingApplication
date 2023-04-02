package com.umut.shoppingapplication.utils.input_watchers

import android.text.Editable
import android.text.TextWatcher
import com.umut.shoppingapplication.databinding.FragmentShoppingCartAndPaymentBinding

/**
 * Validates the Watched EditText Expiry Date
 */
class CardExpiryDateInputValidityWatcher(private val binding: FragmentShoppingCartAndPaymentBinding) : TextWatcher {

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    override fun afterTextChanged(s: Editable) {
        s.apply {
            if (isNotEmpty()) {
                if (length == 1) {
                    if (get(0) != '0' || get(0) != '1') {
                        binding.cardExpiryDateTextInputLayout.error = "Girilen tarih hatalı."
                    } else {
                        binding.cardExpiryDateTextInputLayout.isErrorEnabled = false
                    }
                }

                if (length >= 5) {
                    if (get(3) == '0' || get(3) == '1') {
                        binding.cardExpiryDateTextInputLayout.error = "Girilen tarih hatalı."
                    } else if (get(3) == '2' && get(4) == '0' || get(3) == '2' && get(4) == '1' || get(3) == '2' && get(4) == '2') {
                        binding.cardExpiryDateTextInputLayout.error = "Girilen tarih hatalı."
                    } else {
                        binding.cardExpiryDateTextInputLayout.isErrorEnabled = false
                    }
                }
            }
        }
    }
}