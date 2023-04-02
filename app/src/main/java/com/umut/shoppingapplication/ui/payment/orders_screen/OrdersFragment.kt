package com.umut.shoppingapplication.ui.payment.orders_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.umut.shoppingapplication.databinding.FragmentOrdersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrdersFragment : Fragment() {

    val ordersFragmentViewModel: OrdersFragmentViewModel by viewModels()

    lateinit var binding: FragmentOrdersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrdersBinding.inflate(inflater, container, false)


        configureOrdersRecyclerView()

        observeLiveDataChanges()

        ordersFragmentViewModel.getAllOrdersFromRepository()

        return binding.root
    }

    private fun configureOrdersRecyclerView() {
//        binding.ordersRecyclerView.adapter = ordersRecyclerViewAdapter
//        binding.productsRecyclerView.layoutManager = LinearLayoutManager(
//            requireContext(),
//            LinearLayoutManager.VERTICAL,
//            false
//        )
    }

    private fun observeLiveDataChanges() {
        with(ordersFragmentViewModel) {
            ordersLiveData.observe(viewLifecycleOwner) {

            }
        }
    }
}