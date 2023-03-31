package com.umut.shoppingapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.umut.shoppingapplication.R
import com.umut.shoppingapplication.databinding.FragmentShoppingBinding
import com.umut.shoppingapplication.ui.viewmodels.ShoppingCartFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingFragment : Fragment() {

    lateinit var binding: FragmentShoppingBinding

    val shoppingCartFragmentViewModel: ShoppingCartFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShoppingBinding.inflate(inflater, container, false)

        configureListeners()

        return binding.root
    }

    private fun configureListeners() {
        configureClickListeners()
    }

    private fun configureClickListeners() {
        binding.button.setOnClickListener {
            Navigation.findNavController(
                requireActivity(), R.id.main_fragment_container_view
            ).navigate(R.id.action_shoppingFragment_to_shoppingCartFragment)
        }
    }

}