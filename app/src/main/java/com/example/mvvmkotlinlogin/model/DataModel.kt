package com.example.mvvmkotlinlogin.model

data class DataModel(
    val token: String,
    val user_display_name: String,
    val user_email: String,
    val user_nicename: String,
    val code: String,
    val data: Data,
    val message: String
)