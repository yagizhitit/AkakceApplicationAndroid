package com.example.akakceapplicationandroid.data.repository

import com.example.akakceapplicationandroid.data.remote.RetrofitInstance

class ProductRepository {
    suspend fun getProducts() = RetrofitInstance.api.getProducts()
}