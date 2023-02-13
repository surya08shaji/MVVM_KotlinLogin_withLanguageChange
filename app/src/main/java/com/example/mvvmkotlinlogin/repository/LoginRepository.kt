package com.example.mvvmkotlinlogin.repository

import com.example.mvvmkotlinlogin.data.ApiInterface
import com.example.mvvmkotlinlogin.model.DataModel
import retrofit2.Call

class LoginRepository(private val apiInterface: ApiInterface) {
    fun login(username:String,password:String): Call<DataModel> {
        return apiInterface.login(username,password)
    }
}