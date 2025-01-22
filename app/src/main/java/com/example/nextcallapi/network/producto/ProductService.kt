package com.example.nextcallapi.network.producto

import com.example.nextcallapi.network.RetrofiHelper
import com.example.nextcallapi.network.producto.model.ProductListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductService {
    val retrofit = RetrofiHelper.getRetrofit()

    suspend fun getAllProducts(): ProductListResponse{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(ProductClient::class.java).getAllProducts()
            return@withContext response.body()!!
        }
    }
}