package com.example.akakceapplicationandroid.data.remote

import com.example.akakceapplicationandroid.data.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    fun getProducts(): Call<List<Product>>
}