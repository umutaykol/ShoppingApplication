package com.umut.shoppingapplication.ui.payment.shopping_cart_and_payment_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.umut.shoppingapplication.R
import com.umut.shoppingapplication.databinding.FragmentShoppingCartAndPaymentBinding
import com.umut.shoppingapplication.models.Order
import com.umut.shoppingapplication.utils.Constants
import com.umut.shoppingapplication.utils.Constants.amount
import com.umut.shoppingapplication.utils.format_watchers.CardExpiryDateFormatterWatcher
import com.umut.shoppingapplication.utils.format_watchers.FourDigitCardFormatterWatcher
import com.umut.shoppingapplication.utils.luhn_algorithm.checkLuhnAlgorithm
import com.umut.shoppingapplication.utils.showLongToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingCartAndPaymentFragment : Fragment() {

    lateinit var binding: FragmentShoppingCartAndPaymentBinding

    val shoppingCartAndPaymentFragmentViewModel: ShoppingCartAndPaymentFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShoppingCartAndPaymentBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar.root)
        binding.toolbar.root.setTitle(R.string.shopping_cart_and_payment_toolbar_title)

        arguments?.getFloat(amount)?.let {
            shoppingCartAndPaymentFragmentViewModel.amount = it
        }

        binding.priceOfAmountTextView.text = "${shoppingCartAndPaymentFragmentViewModel.amount} ₺"


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
            showLongToast(requireContext(), binding.cardNumberTextInputEditText.text.toString().checkLuhnAlgorithm().toString())
            navigateToPaymentResult()
        }
    }

    private fun navigateToPaymentResult() {
        val bundle: Bundle = bundleOf()

        bundle.putParcelable(
            Constants.order, Order(
                creditCardNumber = binding.cardNumberTextInputEditText.text.toString(),
                orderAmount = shoppingCartAndPaymentFragmentViewModel.amount,
                orderDate = "10.10.2023"
            )
        )

        Navigation.findNavController(
            requireActivity(), R.id.main_fragment_container_view
        ).navigate(R.id.action_shoppingCartandPaymentFragment_to_paymentResultFragment, bundle)
    }
}