package com.umut.shoppingapplication.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.umut.shoppingapplication.database.FakeProductsRepository
import com.umut.shoppingapplication.models.Product
import com.umut.shoppingapplication.rules.MainCoroutineRule
import com.umut.shoppingapplication.utils.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class FakeShoppingViewModel {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    private lateinit var viewModel: ShoppingFragmentViewModel

    @Before
    fun setup() {
        val fakeNotesRepository = FakeProductsRepository()
        viewModel = ShoppingFragmentViewModel(fakeNotesRepository)
    }

    private val testProduct1 = Product(id = "12345", productName = "product1", productPrice = 1.0F)
    private val testProduct2 = Product(id = "54321", productName= "product2", productPrice = 2.5F)
    private val testProduct3 = Product(id = "15243", productName= "product3", productPrice = 7.2F)

    @Test
    fun `getAllProducts with empty repository returns size of 0`() {
        viewModel.getAllProductsFromRepository()

        val value = viewModel.productsLiveData.getOrAwaitValue()

        assertThat(value?.size).isEqualTo(0)
    }

}