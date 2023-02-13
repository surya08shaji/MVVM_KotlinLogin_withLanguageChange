package com.example.mvvmkotlinlogin.data

import com.example.mvvmkotlinlogin.data.CategoryUrls.baseUrl
import com.example.mvvmkotlinlogin.model.CategoryData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CategoryInterface {
    @GET("v2/5ec39cba300000720039c1f6")
    fun getCategory(): Call<CategoryData>

    companion object Factory {

        fun create(): CategoryInterface {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            val retrofit = Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()).baseUrl(baseUrl)
                .client(client)
                .build()
            return retrofit.create(CategoryInterface::class.java)
        }
    }
}