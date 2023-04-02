package com.umut.shoppingapplication.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.umut.shoppingapplication.database.FakeProductsRepository
import com.umut.shoppingapplication.models.Product
import com.umut.shoppingapplication.rules.MainCoroutineRule
import com.umut.shoppingapplication.ui.payment.shopping_screen.ShoppingFragmentViewModel
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
        val fakeProductsRepository = FakeProductsRepository()
        viewModel = ShoppingFragmentViewModel(fakeProductsRepository)
    }

    private val testProduct1Id = "12345"
    private val testProduct2Id = "54321"
    private val testProduct3Id = "15243"

    private val testProduct1ProductName = "product1"
    private val testProduct2ProductName = "product2"
    private val testProduct3ProductName = "product3"

    private val testProduct1ProductPrice = 1.0F
    private val testProduct2ProductPrice = 2.5F
    private val testProduct3ProductPrice = 7.2F

    private val testProduct1 = Product(id = testProduct1Id, productName = testProduct1ProductName, productPrice = testProduct1ProductPrice, productCount = 1)
    private val testProduct2 = Product(id = testProduct2Id, productName = testProduct2ProductName, productPrice = testProduct2ProductPrice, productCount = 1)
    private val testProduct3 = Product(id = testProduct3Id, productName = testProduct3ProductName, productPrice = testProduct3ProductPrice, productCount = 1)

    @Test
    fun `getAllProducts with empty repository returns size of 0`() {
        viewModel.getAllProductsFromDB()

        val value = viewModel.productsLiveData.getOrAwaitValue()

        assertThat(value?.size).isEqualTo(0)
    }

    @Test
    fun `after inserted new Product contains inserted Product`() {
        viewModel.addProductToDB(testProduct1)

        val value = viewModel.productsLiveData.getOrAwaitValue()

        assertThat(value?.get(0)?.id).isEqualTo("12345")
    }

    @Test
    fun `after deleting all products from DB productsLiveData size should be 0`() {
        viewModel.addAllDummyProductsToDB()

        viewModel.deleteAllProductsFromDB()

        val value = viewModel.productsLiveData.getOrAwaitValue()

        assertThat(value?.size).isEqualTo(0)
    }

    @Test
    fun `after updating existed product old one should be updated`() {
        viewModel.addProductToDB(testProduct1)

        viewModel.updateProductToDB(testProduct1.copy(productPrice = 5.0F))
        viewModel.getAllProductsFromDB()

        val value = viewModel.productsLiveData.getOrAwaitValue()

        assertThat(value?.get(0)?.productPrice).isEqualTo(5.0F)
        assertThat(value?.get(0)?.productPrice).isNotEqualTo(testProduct1ProductPrice)
    }

    @Test
    fun `after add product isCartIsNotEmpty() returns true`() {
        viewModel.addProductToDB(testProduct1, testProduct2, testProduct3)

        assertThat(viewModel.isCartIsNotEmpty()).isEqualTo(true)
    }

    @Test
    fun `before add product isCartIsNotEmpty() returns false`() {
        assertThat(viewModel.isCartIsNotEmpty()).isEqualTo(false)
    }

    @Test
    fun `after all test products inserted getFullAmount() returns full amount`() {
        viewModel.addProductToDB(testProduct1, testProduct2, testProduct3)

        assertThat(viewModel.getFullAmount()).isEqualTo(testProduct1ProductPrice + testProduct2ProductPrice + testProduct3ProductPrice)
    }



}