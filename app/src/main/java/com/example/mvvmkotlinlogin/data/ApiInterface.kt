package com.example.mvvmkotlinlogin.data

import com.example.mvvmkotlinlogin.data.Urls.baseUrl
import com.example.mvvmkotlinlogin.model.DataModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiInterface {
    @POST("token")
    fun login(
        @Query("username") username: String,
        @Query("password") password: String
    ): Call<DataModel>

    companion object Factory{
        fun create(): ApiInterface {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            val retrofit = Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}