package com.example.mvvmkotlinlogin.adapter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmkotlinlogin.databinding.ProductRvAdapterBinding
import com.example.mvvmkotlinlogin.model.Product
import com.example.mvvmkotlinlogin.view.ProductDetailsActivity
import com.squareup.picasso.Picasso

class ProductAdapter(private var pList: List<Product>?): RecyclerView.Adapter<ProductAdapter.ViewHolder>()  {
    class ViewHolder (val binding: ProductRvAdapterBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProductRvAdapterBinding.inflate(LayoutInflater.from(parent.context), parent,
            false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(pList!![position]) {
                binding.productTitle.text = this.title
                binding.productPrice.text = this.price.toString()

                Picasso.with(binding.productImage.context)
                    .load(this.imageUrl).into(binding.productImage)

                binding.cardView.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString("title",title)
                    bundle.putString("price", price.toString())
                    bundle.putString("description",description)
                    bundle.putString("imageUrl",imageUrl)

                    val intent = Intent(binding.cardView.context, ProductDetailsActivity::class.java)
                    intent.putExtras(bundle)
                    binding.cardView.context.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return pList?.size ?: 0
    }
}
