package com.example.mvvmkotlinlogin.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmkotlinlogin.databinding.ActivityProductDetailsBinding
import com.squareup.picasso.Picasso

class ProductDetailsActivity : AppCompatActivity() {

    private var _binding: ActivityProductDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_product_details)

        _binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("title")
        val price = intent.getStringExtra("price")
        val description = intent.getStringExtra("description")
        val imageUrl = intent.getStringExtra("imageUrl")

        binding.detailTitle.text = title
        binding.detailPrice.text = price
        binding.detailDescription.text = description
        Picasso.with(binding.detailImage.context).load(imageUrl).into(binding.detailImage)

        binding.backIcon.setOnClickListener {
            onBackPressed()
        }
    }
}