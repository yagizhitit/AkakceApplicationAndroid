package com.example.akakceapplicationandroid.ui.detail


import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.example.akakceapplicationandroid.R
import com.example.akakceapplicationandroid.data.model.Product
import com.example.akakceapplicationandroid.data.remote.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailedProductActivity : AppCompatActivity() {

    private lateinit var productCategory: TextView
    private lateinit var productImage: ImageView
    private lateinit var productTitle: TextView
    private lateinit var productPrice: TextView
    private lateinit var productRating: TextView
    private lateinit var productRatingCount: TextView
    private lateinit var productDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_product)

        // Toolbar'da geri butonu göstermek
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)



        // Görünüm öğelerini bağlama
        productCategory = findViewById(R.id.productCategory)
        productImage = findViewById(R.id.productImage)
        productTitle = findViewById(R.id.productTitle)
        productPrice = findViewById(R.id.productPrice)
        productRating = findViewById(R.id.productRating)
        productRatingCount = findViewById(R.id.productRatingCount)
        productDescription = findViewById(R.id.productDescription)

        // Intent'ten ürün ID'sini al
        val productId = intent.getIntExtra("PRODUCT_ID", -1)
        if (productId != -1) {
            fetchProductDetails(productId)
        } else {
            Toast.makeText(this, "Ürün ID'si alınamadı", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchProductDetails(productId: Int) {
        // API çağrısını başlat
        val call = RetrofitInstance.api.getProductById(productId)
        call.enqueue(object : Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                if (response.isSuccessful && response.body() != null) {
                    val product = response.body()
                    displayProductDetails(product)
                } else {
                    Toast.makeText(this@DetailedProductActivity, "Ürün detayları alınamadı", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                Toast.makeText(this@DetailedProductActivity, "Hata: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun displayProductDetails(product: Product?) {
        product?.let {
            productCategory.text = it.category
            productTitle.text = it.title
            productPrice.text = String.format("%.2f TL", it.price)
            productRating.text = "Rating: ${it.rating.rate}"
            productRatingCount.text = "(${it.rating.count} reviews)"
            productDescription.text = it.description

            // Glide ile ürün görselini yükleme
            Glide.with(this)
                .load(it.image)
                .placeholder(R.drawable.akakcelogo) // Yer tutucu görsel
                .into(productImage)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}