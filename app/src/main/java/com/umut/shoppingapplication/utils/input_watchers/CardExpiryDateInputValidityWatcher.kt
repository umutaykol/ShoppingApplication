package com.umut.shoppingapplication.utils.input_watchers

import android.text.Editable
import android.text.TextWatcher
import com.umut.shoppingapplication.databinding.FragmentShoppingCartAndPaymentBinding
import com.umut.shoppingapplication.utils.Constants.character_one
import com.umut.shoppingapplication.utils.Constants.character_two
import com.umut.shoppingapplication.utils.Constants.character_zero

/**
 * Validates the Watched EditText Expiry Date
 */
class CardExpiryDateInputValidityWatcher(private val binding: FragmentShoppingCartAndPaymentBinding) : TextWatcher {

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    override fun afterTextChanged(s: Editable) {
        s.apply {
            if (isNotEmpty()) {
                // Validate is first digit is 0 or 1 ? Because months can't start with another number.
                if (length == 1) {
                    if (get(0) != character_zero || get(0) != character_one) {
                        binding.cardExpiryDateTextInputLayout.error = "Girilen tarih hatalı."
                    } else {
                        binding.cardExpiryDateTextInputLayout.isErrorEnabled = false
                    }
                }


                // Validate is entered year is bigger than current year ?
                if (length >= 5) {
                    if (get(3) == character_zero || get(3) == character_one) {
                        binding.cardExpiryDateTextInputLayout.error = "Girilen tarih hatalı."
                    } else if (get(3) == character_two && get(4) == character_zero || get(3) == character_two && get(4) == character_one || get(3) == character_two && get(4) == character_two) {
                        binding.cardExpiryDateTextInputLayout.error = "Girilen tarih hatalı."
                    } else {
                        binding.cardExpiryDateTextInputLayout.isErrorEnabled = false
                    }
                }
            }
        }
    }
}