package com.example.mvvmkotlinlogin.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmkotlinlogin.model.Category
import com.example.mvvmkotlinlogin.model.CategoryData
import com.example.mvvmkotlinlogin.repository.CategoryRepository
import retrofit2.Call
import retrofit2.Response

class CategoryViewModel(private val categoryRepository: CategoryRepository): ViewModel() {
    val categoryList = MutableLiveData<List<Category>>()
    val errorMessage = MutableLiveData<String>()

    fun getCategory(){
        val response = categoryRepository.getCategory()
        response.enqueue(object :retrofit2.Callback<CategoryData>{
            override fun onResponse(call: Call<CategoryData>, response: Response<CategoryData>) {
                if (response.isSuccessful){
                    val categoryData = response.body()
                    if (categoryData != null){
                        val categoryModel = categoryData.categories
                        categoryList.postValue(categoryModel)
                    }
                }
            }

            override fun onFailure(call: Call<CategoryData>, t: Throwable) {
                t.printStackTrace()
                Log.e("scroll", "exception",t)
            }

        })
    }
}