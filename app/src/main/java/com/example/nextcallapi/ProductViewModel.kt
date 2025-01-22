package com.example.nextcallapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nextcallapi.network.producto.model.ProductRepository
import com.example.nextcallapi.network.producto.model.ProductResponse
import kotlinx.coroutines.launch

class ProductViewModel: ViewModel() {
    private val productListRepository = ProductRepository()

    private val _productList = MutableLiveData<List<ProductResponse>>(emptyList())
    val ProductList: LiveData<List<ProductResponse>> = _productList

    private val _isloading = MutableLiveData<Boolean>()
    val isloading: LiveData<Boolean> = _isloading

    fun getAllProduct(){
        viewModelScope.launch {
            _isloading.value = true
            _productList.postValue(productListRepository.getAllProducts().productList)
            _isloading.value = false
        }
    }
}
