package com.umut.shoppingapplication.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.umut.shoppingapplication.database.FakeOrdersRepository
import com.umut.shoppingapplication.models.Order
import com.umut.shoppingapplication.rules.MainCoroutineRule
import com.umut.shoppingapplication.ui.payment.payment_result_screen.PaymentResultFragmentViewModel
import com.umut.shoppingapplication.utils.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class FakePaymentResultViewModel {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: PaymentResultFragmentViewModel

    @Before
    fun setup() {
        val fakeOrdersRepository = FakeOrdersRepository()
        viewModel = PaymentResultFragmentViewModel(fakeOrdersRepository)
    }

    private val testOrder1Id = "12345"

    private val testOrder1OrderDate = "01.01.2023"

    private val testOrder1CreditCardNumber = "1234 1234 1234 1234"

    private val testOrder1OrderAmount = 20F

    private val testOrder1 = Order(
        id = testOrder1Id,
        orderDate = testOrder1OrderDate,
        creditCardNumber = testOrder1CreditCardNumber,
        orderAmount = testOrder1OrderAmount
    )

    @Test
    fun `after inserting orders ordersLiveData should contains inserted order`() {
        viewModel.insertOrdersToRepository(testOrder1)

        val value = viewModel.ordersLiveData.getOrAwaitValue()

        assertThat(value?.size).isEqualTo(1)
        assertThat(value?.get(0)).isEqualTo(testOrder1)
    }

}