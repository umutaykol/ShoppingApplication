package com.umut.shoppingapplication.utils.input_watchers

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.umut.shoppingapplication.databinding.FragmentShoppingCartAndPaymentBinding
import com.umut.shoppingapplication.utils.Constants
import com.umut.shoppingapplication.utils.luhn_algorithm.checkLuhnAlgorithm

/**
 * Validates the Watched EditText Credit Card Number
 */
class CardNumberInputValidityWatcher(private val binding: FragmentShoppingCartAndPaymentBinding) : TextWatcher {

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    override fun afterTextChanged(s: Editable) {
        s.apply {
            if (isNotEmpty()) {
                if (get(0) == Constants.character_four && length == 19) {
                    if (toString().checkLuhnAlgorithm()) {
                        setCardTypeLayoutVisibility(isVisa = true)
                        binding.cardNumberTextInputLayout.isErrorEnabled = false
                    } else {
                        binding.cardNumberTextInputLayout.error = "Kart numaranız hatalı."
                    }
                } else if (get(0) == Constants.character_five && length == 19) {
                    if (toString().checkLuhnAlgorithm()) {
                        setCardTypeLayoutVisibility(isMastercard = true)
                        binding.cardNumberTextInputLayout.isErrorEnabled = false
                    } else {
                        binding.cardNumberTextInputLayout.error = "Kart numaranız hatalı."
                    }
                } else if (length == 19) {
                    if (toString().checkLuhnAlgorithm()) {

                    } else {
                        binding.cardNumberTextInputLayout.error = "Kart numaranız hatalı."
                    }
                } else {
                    binding.cardNumberTextInputLayout.isErrorEnabled = false
                    setCardTypeLayoutVisibility(isNone = true)
                }
            } else {
                binding.cardNumberTextInputLayout.isErrorEnabled = false
                setCardTypeLayoutVisibility(isNone = true)
            }
        }
    }

    private fun setCardTypeLayoutVisibility(
        isNone: Boolean = false,
        isVisa: Boolean = false,
        isMastercard: Boolean = false
    ) {
        if (isNone) {
            binding.visaCardRelativeLayout.visibility = View.GONE
            binding.mastercardRelativeLayout.visibility = View.GONE
        } else if (isVisa) {
            binding.visaCardRelativeLayout.visibility = View.VISIBLE
            binding.mastercardRelativeLayout.visibility = View.GONE
        } else if (isMastercard) {
            binding.mastercardRelativeLayout.visibility = View.VISIBLE
            binding.visaCardRelativeLayout.visibility = View.GONE
        }
    }
}