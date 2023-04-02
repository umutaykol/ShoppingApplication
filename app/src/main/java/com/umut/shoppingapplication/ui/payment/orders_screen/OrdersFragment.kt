package com.umut.shoppingapplication.ui.payment.orders_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.umut.shoppingapplication.adapters.orders.OrderItemClickListener
import com.umut.shoppingapplication.databinding.FragmentOrdersBinding
import com.umut.shoppingapplication.models.Order
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrdersFragment : Fragment(), OrderItemClickListener {

    private val ordersFragmentViewModel: OrdersFragmentViewModel by viewModels()

    lateinit var binding: FragmentOrdersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrdersBinding.inflate(inflater, container, false)

        configureOrdersRecyclerView()

        observeLiveDataChanges()

        ordersFragmentViewModel.getAllOrdersFromRepository()

        TODO("Orderlar silinebilecek.")

        return binding.root
    }

    private fun configureOrdersRecyclerView() {
        TODO("Orderlar için Recycler View Configure Edilecek.")
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

    override fun deleteOrderClicked(view: View, order: Order, position: Int) {
        showAlertDialog(
            title = "UYARI",
            message = "Sipariş silinecektir, emin misiniz?",
            negativeButtonText = "Hayır",
            positiveButtonText = "Evet",
            negativeButtonFunction = { /* Do Nothing */ },
            positiveButtonFunction = { TODO("Sipariş DB'den silinecek.") }
        )
    }

    private fun showAlertDialog(
        title: String,
        message: String,
        negativeButtonText: String,
        positiveButtonText: String,
        negativeButtonFunction: () -> Unit,
        positiveButtonFunction: () -> Unit
    ) {
        AlertDialog.Builder(requireContext())
            .setMessage(message)
            .setTitle(title)
            .setCancelable(false)
            .setNegativeButton(negativeButtonText) { _, _ -> negativeButtonFunction() }
            .setPositiveButton(positiveButtonText) { _, _ -> positiveButtonFunction() }
            .create()
            .show()
    }

}