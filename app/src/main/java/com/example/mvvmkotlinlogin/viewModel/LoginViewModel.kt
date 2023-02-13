package com.example.mvvmkotlinlogin.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmkotlinlogin.repository.LoginRepository
import com.example.mvvmkotlinlogin.model.DataModel
import retrofit2.Call
import retrofit2.Response

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel(){
    var toast = MutableLiveData<String>()
    val loginData = MutableLiveData<DataModel>()
    val errorMessage = MutableLiveData<String>()

    fun login(username:String,password:String){
        val response = loginRepository.login(username,password)
        response.enqueue(object : retrofit2.Callback<DataModel>{
            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                if (response.isSuccessful){
                    val dataCode = response.code()
                    if (dataCode == 200){
                        loginData.postValue(response.body())
                    } else {
                        toast.postValue(response.message())
                    }
                } else{
                    toast.postValue(response.message())
                }
            }

            override fun onFailure(call: Call<DataModel>, t: Throwable) {
               errorMessage.postValue(t.message)
            }

        })
    }
}
