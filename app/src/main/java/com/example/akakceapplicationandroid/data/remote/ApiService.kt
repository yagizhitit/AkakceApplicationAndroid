package com.example.akakceapplicationandroid.data.remote

import com.example.akakceapplicationandroid.data.model.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("products")
    fun getProducts(
        @Query("limit") limit: Int? = null // Nullable ve varsayılan olarak null
    ): Call<List<Product>>
}