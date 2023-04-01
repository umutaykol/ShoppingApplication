package com.umut.shoppingapplication.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umut.shoppingapplication.models.Product
import com.umut.shoppingapplication.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingFragmentViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
) : ViewModel() {

    val productList: Array<Product> by lazy {
        arrayOf(
            Product(productName = "Kalem", productPrice = 9.58F),
            Product(productName = "Kağıt", productPrice = 0.61F),
            Product(productName = "Silgi", productPrice = 30.0F),
            Product(productName = "Defter", productPrice = 40.0F),
            Product(productName = "Kitap", productPrice = 50.82F)
        )
    }

//    init {
//        deleteAllProductsFromRepository()
//        insertProductsToRepository(*productList)
//    }

    private var _productsMutableLiveData: MutableLiveData<MutableList<Product>?> =
        MutableLiveData<MutableList<Product>?>()
    val productsLiveData: LiveData<MutableList<Product>?>
        get() = _productsMutableLiveData

    /**
     * Database Operations
     */

    private fun deleteProductsFromRepository(vararg product: Product) = viewModelScope.launch {
        productsRepository.deleteAll(*product)
    }

    private fun deleteAllProductsFromRepository() = viewModelScope.launch {
        productsRepository.deleteAllProducts()
    }

    private fun getAllProductsFromRepository() = viewModelScope.launch {
        val products = productsRepository.getAllProducts()
        _productsMutableLiveData.postValue(products)
    }

    private fun updateProductOnRepository(product: Product) = viewModelScope.launch {
        productsRepository.updateProduct(product)
    }

//    private fun insertProductToRepository(product: Product) = viewModelScope.launch {
//        productsRepository.insertProduct(product)
//    }

    private fun insertProductsToRepository(vararg products: Product) = viewModelScope.launch {
        productsRepository.insertAll(*products)
    }

    /**
     * Database Operations
     */

    fun getAllProductsFromDB() {
        getAllProductsFromRepository()
    }

    fun addAllProductsToDB() {
        insertProductsToRepository(*productList)
    }

    fun updateProductToDB(product: Product) {
        updateProductOnRepository(product)
    }

}