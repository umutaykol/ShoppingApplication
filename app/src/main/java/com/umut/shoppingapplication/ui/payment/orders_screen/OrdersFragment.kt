package com.umut.shoppingapplication.ui.payment.orders_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.umut.shoppingapplication.R
import com.umut.shoppingapplication.adapters.orders.OrderItemClickListener
import com.umut.shoppingapplication.adapters.orders.OrdersRecyclerViewAdapter
import com.umut.shoppingapplication.databinding.FragmentOrdersBinding
import com.umut.shoppingapplication.models.Order
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrdersFragment : Fragment(), OrderItemClickListener {

    private val ordersRecyclerViewAdapter = OrdersRecyclerViewAdapter(this)

    private val ordersFragmentViewModel: OrdersFragmentViewModel by viewModels()

    private lateinit var binding: FragmentOrdersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrdersBinding.inflate(inflater, container, false)

        configureOrdersRecyclerView()
        configureToolbarTitle()
        observeLiveDataChanges()

        ordersFragmentViewModel.getAllOrdersFromRepository()

        return binding.root
    }

    private fun configureOrdersRecyclerView() {
        binding.ordersRecyclerView.adapter = ordersRecyclerViewAdapter
        binding.ordersRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    private fun configureToolbarTitle() {
        binding.toolbar.root.setTitle(R.string.shopping_cart_and_payment_toolbar_title)
    }

    private fun observeLiveDataChanges() {
        with(ordersFragmentViewModel) {
            ordersLiveData.observe(viewLifecycleOwner) {
                if (it?.size == 0 || it == null) {
                    setVisibilityNoContentLottieAnimation(isVisible = true)
                } else {
                    setVisibilityNoContentLottieAnimation(isVisible = false)
                }

                addOrdersToRecyclerView(it)
            }
        }
    }

    private fun setVisibilityNoContentLottieAnimation(isVisible: Boolean = false) {
        if (isVisible) {
            binding.noContentLottieAnimationView.visibility = View.VISIBLE
            binding.ordersRecyclerView.visibility = View.GONE
        } else {
            binding.noContentLottieAnimationView.visibility = View.GONE
            binding.ordersRecyclerView.visibility = View.VISIBLE
        }
    }

    private fun addOrdersToRecyclerView(orders: MutableList<Order>?) {
        ordersRecyclerViewAdapter.differ.submitList(orders)
        ordersRecyclerViewAdapter.notifyDataSetChanged()
    }

    override fun deleteOrderClicked(view: View, order: Order, position: Int) {
        showAlertDialog(
            title = "UYARI",
            message = "Sipariş silinecektir, emin misiniz?",
            negativeButtonText = "Hayır",
            positiveButtonText = "Evet",
            negativeButtonFunction = { /* Do Nothing */ },
            positiveButtonFunction = { ordersFragmentViewModel.deleteOrderFromDB(order) }
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