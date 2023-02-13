package com.example.mvvmkotlinlogin.model

data class CategoryData(
    val categories: List<Category>,
    val msg: String,
    val status: Boolean
)