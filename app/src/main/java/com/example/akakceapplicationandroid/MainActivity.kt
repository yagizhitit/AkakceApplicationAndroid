package com.example.akakceapplicationandroid

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.akakceapplicationandroid.data.repository.ProductRepository
import com.example.akakceapplicationandroid.ui.adapter.CardProductAdapter
import com.example.akakceapplicationandroid.ui.adapter.ListProductAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var cardRecyclerView: RecyclerView
    private lateinit var listRecyclerView: RecyclerView

    private lateinit var cardAdapter: CardProductAdapter
    private lateinit var listAdapter: ListProductAdapter
    private val repository = ProductRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Edge-to-edge padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // RecyclerView setup
        cardRecyclerView = findViewById(R.id.card_recycler_view)
        cardRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        cardAdapter = CardProductAdapter(emptyList()) // Boş bir listeyle adapter oluştur
        cardRecyclerView.adapter = cardAdapter

        listRecyclerView = findViewById(R.id.list_recycler_view)
        listRecyclerView.layoutManager = GridLayoutManager(this, 2) // Her satırda 2 öğe
        listAdapter = ListProductAdapter(emptyList())
        listRecyclerView.adapter = listAdapter


        // Fetch products
        fetchProducts()
    }

    private fun fetchProducts() {
        repository.getProducts { products ->
            if (products != null) {
                runOnUiThread {
                    cardAdapter = CardProductAdapter(products) // Yeni ürünlerle adapter'ı güncelle
                    cardRecyclerView.adapter = cardAdapter // RecyclerView'e bağla

                    listAdapter = ListProductAdapter(products)
                    listRecyclerView.adapter = listAdapter
                }
            } else {
                println("Ürünler yüklenemedi!")
            }
        }
    }

    private fun enableEdgeToEdge() {
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }
}