package com.example.akakceapplicationandroid.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.akakceapplicationandroid.R
import com.example.akakceapplicationandroid.data.model.Product
import com.example.akakceapplicationandroid.ui.detail.DetailedProductActivity

class CardProductAdapter(private val products: List<Product>) :
    RecyclerView.Adapter<CardProductAdapter.ProductViewHolder>() {

    private var onItemClickListener: ((Product) -> Unit)? = null

    // ViewHolder sınıfı
    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.product_image)
        val productName: TextView = itemView.findViewById(R.id.product_name)
        val productPrice: TextView = itemView.findViewById(R.id.product_price)
        val productRating: TextView = itemView.findViewById(R.id.rating_value)
        val productRatingCount: TextView = itemView.findViewById(R.id.rating_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]

        // Verileri ViewHolder'a bağlama
        holder.productName.text = product.title
        holder.productPrice.text = String.format("%.2f TL", product.price)
        holder.productRating.text = product.rating.rate.toString()
        holder.productRatingCount.text = "(${product.rating.count} reviews)"

        // Glide kullanarak görsel yükleme
        Glide.with(holder.productImage.context)
            .load(product.image)
            .placeholder(R.drawable.akakcelogo)
            .into(holder.productImage)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailedProductActivity::class.java).apply {
                putExtra("PRODUCT_ID", product.id) // Sadece ürün ID'sini gönderiyoruz
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = products.size

    // Tıklama dinleyicisi için bir metot ekle
    fun setOnItemClickListener(listener: (Product) -> Unit) {
        onItemClickListener = listener
    }
}