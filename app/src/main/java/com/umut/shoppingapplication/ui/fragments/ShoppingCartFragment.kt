package com.umut.shoppingapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umut.shoppingapplication.databinding.FragmentShoppingBinding
import com.umut.shoppingapplication.databinding.FragmentShoppingCartBinding

class ShoppingCartFragment : Fragment() {

    lateinit var binding: FragmentShoppingCartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShoppingCartBinding.inflate(inflater, container, false)

        return binding.root
    }
}