package com.umut.shoppingapplication.ui.payment.payment_result_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.umut.shoppingapplication.R
import com.umut.shoppingapplication.databinding.FragmentPaymentResultBinding
import com.umut.shoppingapplication.utils.Constants.order
import com.umut.shoppingapplication.utils.Constants.payment_result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentResultFragment : Fragment() {

    lateinit var binding: FragmentPaymentResultBinding

    private val paymentResultFragmentViewModel: PaymentResultFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        paymentResultFragmentViewModel.order = arguments?.getParcelable(order)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPaymentResultBinding.inflate(inflater, container, false)

        configureOnBackPressed()
        configureOptionMenuAndActionBarSupporting()
        configureToolbarTitle()
        configureListeners()

        paymentResultFragmentViewModel.order?.let {
            paymentResultFragmentViewModel.insertOrdersToRepository(it)
            binding.order = it
        }

        return binding.root
    }

    private fun configureOnBackPressed() {
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {}
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)
    }

    private fun configureToolbarTitle() {
        binding.toolbar.root.setTitle(R.string.payment_result_screen)
    }

    private fun configureOptionMenuAndActionBarSupporting() {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar.root)
    }

    private fun configureListeners() {
        configureClickListeners()
    }

    private fun configureClickListeners() {
        binding.returnToShoppingScreenButton.setOnClickListener {
            navigateToShopping()
        }
    }

    private fun navigateToShopping() {
        val bundle = bundleOf()

        bundle.putBoolean(payment_result, true)

        Navigation.findNavController(
            requireActivity(), R.id.main_fragment_container_view
        ).navigate(R.id.action_paymentResultFragment_to_shoppingFragment, bundle)
    }

}