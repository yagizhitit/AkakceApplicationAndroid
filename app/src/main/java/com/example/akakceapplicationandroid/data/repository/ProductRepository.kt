package com.example.akakceapplicationandroid.data.repository

import com.example.akakceapplicationandroid.data.model.Product
import com.example.akakceapplicationandroid.data.remote.RetrofitInstance

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepository {
    fun getProducts(callback: (List<Product>?) -> Unit) {
        RetrofitInstance.api.getProducts().enqueue(object : Callback<List<Product>> {
            override fun onResponse(
                call: Call<List<Product>>,
                response: Response<List<Product>>
            ) {
                if (response.isSuccessful) {
                    callback(response.body()) // Başarılı yanıtı döndür
                } else {
                    callback(null) // Hata durumunda null döndür
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                t.printStackTrace()
                callback(null) // Hata durumunda null döndür
            }
        })
    }
}