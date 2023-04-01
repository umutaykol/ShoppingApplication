package com.umut.shoppingapplication.ui.payment.shopping_cart_and_payment_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.umut.shoppingapplication.R
import com.umut.shoppingapplication.databinding.FragmentShoppingCartAndPaymentBinding
import com.umut.shoppingapplication.utils.format_watchers.CardExpiryDateFormatterWatcher
import com.umut.shoppingapplication.utils.format_watchers.FourDigitCardFormatterWatcher


class ShoppingCartAndPaymentFragment : Fragment() {

    lateinit var binding: FragmentShoppingCartAndPaymentBinding

    val shoppingCardFragmentViewModel: ShoppingCartAndPaymentFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShoppingCartAndPaymentBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar.root)
        binding.toolbar.root.setTitle(R.string.shopping_cart_and_payment_toolbar_title)

        val amount: Float? = arguments?.getFloat("amount")
        binding.priceOfAmountTextView.text = "$amount ₺"


        binding.cardNumberTextInputEditText.addTextChangedListener(FourDigitCardFormatterWatcher())
        binding.cardExpiryDateTextInputEditText.addTextChangedListener(CardExpiryDateFormatterWatcher())

        configureListeners()

        return binding.root
    }

    private fun configureListeners() {
        configureClickListeners()
    }

    private fun configureClickListeners() {
        binding.consentOrderButton.setOnClickListener {
            navigateToPaymentResult()
        }
    }

    private fun navigateToPaymentResult() {
            Navigation.findNavController(
                requireActivity(), R.id.main_fragment_container_view
            ).navigate(R.id.action_shoppingCartandPaymentFragment_to_paymentResultFragment)

    }
}