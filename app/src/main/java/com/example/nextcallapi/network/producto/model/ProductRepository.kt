package com.example.nextcallapi.network.producto.model

import com.example.nextcallapi.network.producto.ProductService

class ProductRepository {
    val api = ProductService()

    suspend fun getAllProducts(): ProductListResponse{
        return api.getAllProducts()
    }
}