package com.umut.shoppingapplication.ui.payment.shopping_cart_and_payment_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.umut.shoppingapplication.R
import com.umut.shoppingapplication.databinding.FragmentShoppingCartAndPaymentBinding
import com.umut.shoppingapplication.models.Order
import com.umut.shoppingapplication.utils.Constants
import com.umut.shoppingapplication.utils.Constants.amount
import com.umut.shoppingapplication.utils.Constants.character_five
import com.umut.shoppingapplication.utils.Constants.character_four
import com.umut.shoppingapplication.utils.format_watchers.CardExpiryDateFormatterWatcher
import com.umut.shoppingapplication.utils.format_watchers.FourDigitCardFormatterWatcher
import com.umut.shoppingapplication.utils.getCurrentDateTime
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

        binding.priceOfAmountTextView.text = "${shoppingCartAndPaymentFragmentViewModel.amount} ₺"

        configureListeners()

        return binding.root
    }

    private fun configureOptionMenuAndActionBarSupporting() {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar.root)
        binding.toolbar.root.setTitle(R.string.shopping_cart_and_payment_toolbar_title)
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

    private fun configureListeners() {
        configureClickListeners()
        configureTextChangedListeners()
    }

    private fun configureTextChangedListeners() {
        binding.cardNumberTextInputEditText.addTextChangedListener(FourDigitCardFormatterWatcher())
        binding.cardExpiryDateTextInputEditText.addTextChangedListener(CardExpiryDateFormatterWatcher())

        binding.cardNumberTextInputEditText.addTextChangedListener {
            it?.let {
                if (it.isNotEmpty()) {
                    if (it[0] == character_four && it.length == 19) {
                        if (it.toString().checkLuhnAlgorithm()) {
                            setCardTypeLayoutVisibility(isVisa = true)
                            binding.cardNumberTextInputLayout.isErrorEnabled = false
                        } else {
                            binding.cardNumberTextInputLayout.error = "Kart numaranız hatalı."
                        }
                    } else if (it[0] == character_five && it.length == 19) {
                        if (it.toString().checkLuhnAlgorithm()) {
                            setCardTypeLayoutVisibility(isMastercard = true)
                            binding.cardNumberTextInputLayout.isErrorEnabled = false
                        } else {
                            binding.cardNumberTextInputLayout.error = "Kart numaranız hatalı."
                        }
                    } else if (it.length == 19) {
                        if (it.toString().checkLuhnAlgorithm()) {

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


        binding.cardExpiryDateTextInputEditText.addTextChangedListener {
            it?.let {
                if (it.isNotEmpty()) {
                    if(it.length == 1) {
                        if(it[0] != '0' || it[0] != '1') {
                            binding.cardExpiryDateTextInputLayout.error = "Girilen tarih hatalı."
                        } else {
                            binding.cardExpiryDateTextInputLayout.isErrorEnabled = false
                        }
                    }

                    if (it.length >= 5) {
                        if (it[3] == '0' || it[3] == '1') {
                            binding.cardExpiryDateTextInputLayout.error = "Girilen tarih hatalı."
                        } else if (it[3] == '2' && it[4] == '0' || it[3] == '2' && it[4] == '1' || it[3] == '2' && it[4] == '2') {
                            binding.cardExpiryDateTextInputLayout.error = "Girilen tarih hatalı."
                        } else {
                            binding.cardExpiryDateTextInputLayout.isErrorEnabled = false
                        }
                    }
                }
            }
        }
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