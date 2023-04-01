package com.umut.shoppingapplication.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.umut.shoppingapplication.R
import com.umut.shoppingapplication.databinding.FragmentShoppingCartAndPaymentBinding
import com.umut.shoppingapplication.ui.viewmodels.ShoppingCartAndPaymentFragmentViewModel
import com.umut.shoppingapplication.utils.showLongToast

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
        binding.toolbar.root.setTitle(R.string.shopping_cart)


        val amount: Float? = arguments?.getFloat("amount")
        showLongToast(requireContext(), amount.toString())

        return binding.root
    }

}