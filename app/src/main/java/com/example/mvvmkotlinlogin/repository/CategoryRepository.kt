package com.example.mvvmkotlinlogin.repository

import com.example.mvvmkotlinlogin.data.CategoryInterface
import com.example.mvvmkotlinlogin.model.CategoryData
import retrofit2.Call

class CategoryRepository(private val categoryInterface: CategoryInterface) {
    fun getCategory(): Call<CategoryData> {
        return categoryInterface.getCategory()
    }
}