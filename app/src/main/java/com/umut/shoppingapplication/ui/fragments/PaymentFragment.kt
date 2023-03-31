package com.umut.shoppingapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.umut.shoppingapplication.R
import com.umut.shoppingapplication.databinding.FragmentPaymentBinding
import com.umut.shoppingapplication.databinding.FragmentPaymentResultBinding
import com.umut.shoppingapplication.ui.viewmodels.PaymentFragmentViewModel
import com.umut.shoppingapplication.ui.viewmodels.PaymentResultViewModel

class PaymentFragment : Fragment() {

    lateinit var binding: FragmentPaymentBinding

    private val paymentFragmentViewModel: PaymentFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaymentBinding.inflate(inflater, container, false)


        return binding.root
    }

}