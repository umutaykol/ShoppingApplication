package com.umut.shoppingapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import com.umut.shoppingapplication.R
import com.umut.shoppingapplication.databinding.FragmentOrdersBinding
import com.umut.shoppingapplication.ui.viewmodels.OrdersFragmentViewModel

class OrdersFragment : Fragment() {

    val ordersFragmentViewModel: OrdersFragmentViewModel by viewModels()

    lateinit var binding: FragmentOrdersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrdersBinding.inflate(inflater, container, false)

        return binding.root
    }
}