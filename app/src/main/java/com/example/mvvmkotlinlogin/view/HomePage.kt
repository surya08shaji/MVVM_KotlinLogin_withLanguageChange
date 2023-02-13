package com.example.mvvmkotlinlogin.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmkotlinlogin.adapter.CategoryAdapter
import com.example.mvvmkotlinlogin.data.CategoryInterface
import com.example.mvvmkotlinlogin.databinding.ActivityHomePageBinding
import com.example.mvvmkotlinlogin.repository.CategoryRepository
import com.example.mvvmkotlinlogin.viewModel.CategoryViewModel
import com.example.mvvmkotlinlogin.viewModelFactory.CategoryViewModelFactory

class HomePage : AppCompatActivity() {

    private var _binding: ActivityHomePageBinding? = null
    private val binding get() = _binding!!

    private val apiCall = CategoryInterface.create()
    private lateinit var adapter: CategoryAdapter

    private lateinit var viewModel: CategoryViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_home_page)

        _binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userNickname = intent.getStringExtra("user_nickname")

        binding.user.text = "Hai, $userNickname"

        viewModel = ViewModelProvider(this,
        CategoryViewModelFactory(CategoryRepository(apiCall)))
            .get(CategoryViewModel::class.java)


        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        adapter = CategoryAdapter()
        binding.recyclerView.adapter = adapter

        viewModel.categoryList.observe(this) {
            adapter.setCategoryList(it)
        }
        viewModel.errorMessage.observe(this) {

        }
        viewModel.getCategory()

    }
}