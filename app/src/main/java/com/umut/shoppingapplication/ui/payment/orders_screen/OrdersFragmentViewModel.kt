package com.umut.shoppingapplication.ui.payment.orders_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umut.shoppingapplication.models.Order
import com.umut.shoppingapplication.repository.orders.OrdersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrdersFragmentViewModel @Inject constructor(
    private val ordersRepository: OrdersRepository
) : ViewModel() {

    private var _ordersMutableLiveData: MutableLiveData<MutableList<Order>?> =
        MutableLiveData<MutableList<Order>?>()
    val ordersLiveData: LiveData<MutableList<Order>?>
        get() = _ordersMutableLiveData

    /**
     * Database Operations
     */

    private fun deleteOrdersFromRepository(vararg orders: Order) = viewModelScope.launch {
        ordersRepository.deleteAll(*orders)
        getAllOrdersFromRepository()
    }

    fun getAllOrdersFromRepository() = viewModelScope.launch {
        val orders = ordersRepository.getAllOrders()
        _ordersMutableLiveData.postValue(orders)
    }

    /**
     * Database Operations
     */

    fun deleteOrderFromDB(order: Order) {
        deleteOrdersFromRepository(order)
    }
}