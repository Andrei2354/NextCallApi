package com.example.nextcallapi.network.producto

import com.example.nextcallapi.network.producto.model.ProductListResponse
import com.example.nextcallapi.network.producto.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductClient {
    @GET("/products")
    suspend fun getAllProducts(): Response<ProductListResponse>

    @GET("/product/{id}")
    suspend fun getAllProductById(id: Int): Response<ProductResponse>
}