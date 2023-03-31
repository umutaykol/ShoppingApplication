package com.umut.shoppingapplication.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.umut.shoppingapplication.R
import com.umut.shoppingapplication.databinding.FragmentShoppingBinding
import com.umut.shoppingapplication.databinding.FragmentShoppingCartBinding
import com.umut.shoppingapplication.ui.viewmodels.ShoppingCartFragmentViewModel

class ShoppingCartFragment : Fragment() {

    lateinit var binding: FragmentShoppingCartBinding

    val shoppingCardFragmentViewModel: ShoppingCartFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShoppingCartBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar.root)
        binding.toolbar.root.setTitle(R.string.shopping_cart)

        return binding.root
    }

}