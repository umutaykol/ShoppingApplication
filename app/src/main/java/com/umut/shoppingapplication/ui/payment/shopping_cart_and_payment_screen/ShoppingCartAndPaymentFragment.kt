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
import com.umut.shoppingapplication.utils.getCurrentDateTime
import com.umut.shoppingapplication.utils.input_watchers.CardExpiryDateInputValidityWatcher
import com.umut.shoppingapplication.utils.input_watchers.CardNumberInputValidityWatcher
import com.umut.shoppingapplication.utils.luhn_algorithm.checkLuhnAlgorithm
import com.umut.shoppingapplication.utils.showLongToast
import com.umut.shoppingapplication.utils.toString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingCartAndPaymentFragment : Fragment() {

    lateinit var binding: FragmentShoppingCartAndPaymentBinding

    private val shoppingCartAndPaymentFragmentViewModel: ShoppingCartAndPaymentFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getBundleArguments()
    }

    private fun getBundleArguments() {
        arguments?.getFloat(amount)?.let {
            shoppingCartAndPaymentFragmentViewModel.amount = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShoppingCartAndPaymentBinding.inflate(inflater, container, false)

        configureOptionMenuAndActionBarSupporting()
        configureToolbarTitle()
        configureListeners()

        binding.priceOfAmountTextView.text = "${shoppingCartAndPaymentFragmentViewModel.amount} ₺"

        return binding.root
    }

    private fun configureToolbarTitle() {
        binding.toolbar.root.setTitle(R.string.shopping_cart_and_payment_toolbar_title)
    }

    private fun configureOptionMenuAndActionBarSupporting() {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar.root)
    }

    private fun configureListeners() {
        configureClickListeners()
        configureTextChangedListeners()
    }

    private fun configureTextChangedListeners() {
        binding.cardNumberTextInputEditText.addTextChangedListener(FourDigitCardFormatterWatcher())
        binding.cardExpiryDateTextInputEditText.addTextChangedListener(CardExpiryDateFormatterWatcher())
        binding.cardNumberTextInputEditText.addTextChangedListener(CardNumberInputValidityWatcher(binding))
        binding.cardExpiryDateTextInputEditText.addTextChangedListener(CardExpiryDateInputValidityWatcher(binding))
    }

    private fun configureClickListeners() {
        binding.consentOrderButton.setOnClickListener {
            showLongToast(
                requireContext(),
                binding.cardNumberTextInputEditText.text.toString().checkLuhnAlgorithm().toString()
            )
            navigateToPaymentResult()
        }
    }

    private fun navigateToPaymentResult() {
        val bundle: Bundle = bundleOf()

        bundle.putParcelable(
            Constants.order, Order(
                creditCardNumber = binding.cardNumberTextInputEditText.text.toString(),
                orderAmount = shoppingCartAndPaymentFragmentViewModel.amount,
                orderDate = getDateWithStringFormat() ?: ""
            )
        )

        Navigation.findNavController(
            requireActivity(), R.id.main_fragment_container_view
        ).navigate(R.id.action_shoppingCartandPaymentFragment_to_paymentResultFragment, bundle)
    }

    private fun getDateWithStringFormat(): String? = getCurrentDateTime().toString("dd.MM.yyyy")

}