package com.example.akakceapplicationandroid.ui.detail

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.example.akakceapplicationandroid.R
import com.example.akakceapplicationandroid.data.model.Product

class DetailedProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_product)

        // View'ları bağlama
        val productCategory: TextView = findViewById(R.id.productCategory)
        val productImage: ImageView = findViewById(R.id.productImage)
        val productTitle: TextView = findViewById(R.id.productTitle)
        val productPrice: TextView = findViewById(R.id.productPrice)
        val productRating: TextView = findViewById(R.id.productRating)
        val productRatingCount: TextView = findViewById(R.id.productRatingCount)
        val productDescription: TextView = findViewById(R.id.productDescription)

        // Intent'ten gelen Product verisi
        val product = intent.getParcelableExtra<Product>("PRODUCT") // @Parcelize kullanıyorsanız getParcelableExtra

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Verileri göster
        product?.let {
            productCategory.text = "${it.category} >"
            productTitle.text = it.title
            productPrice.text = String.format("%.2f TL", it.price)
            productRating.text = "Rating: ${it.rating.rate}" // Rating değeri
            productRatingCount.text = "(${it.rating.count} reviews)" // Rating count değeri
            productDescription.text = it.description

            // Görseli yükleme
            Glide.with(this)
                .load(it.image)
                .placeholder(R.drawable.akakcelogo) // Yer tutucu görsel
                .into(productImage)
        }
    }
    // Geri butonuna tıklama işlemi
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> { // Geri butonu ID'si
                finish() // Aktiviteyi kapatarak bir önceki sayfaya dön
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}