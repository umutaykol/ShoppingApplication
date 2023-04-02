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

    private fun deleteAllOrdersFromRepository() = viewModelScope.launch {
        ordersRepository.deleteAllOrders()
        getAllOrdersFromRepository()
    }

    fun getAllOrdersFromRepository() = viewModelScope.launch {
        val orders = ordersRepository.getAllOrders()
        _ordersMutableLiveData.postValue(orders)
    }

    private fun updateOrderOnRepository(order: Order) = viewModelScope.launch {
        ordersRepository.updateOrder(order)
        getAllOrdersFromRepository()
    }

    private fun insertOrderToRepository(order: Order) = viewModelScope.launch {
        ordersRepository.insertOrder(order)
        getAllOrdersFromRepository()
    }

    fun insertOrdersToRepository(vararg orders: Order) = viewModelScope.launch {
        ordersRepository.insertAll(*orders)
        getAllOrdersFromRepository()
    }

    /**
     * Database Operations
     */

    fun deleteOrderFromDB(order: Order) {
        deleteOrdersFromRepository(order)
    }
}