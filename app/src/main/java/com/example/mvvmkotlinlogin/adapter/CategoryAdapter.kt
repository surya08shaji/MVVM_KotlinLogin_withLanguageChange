package com.example.mvvmkotlinlogin.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmkotlinlogin.databinding.CategoryRvAdapterBinding
import com.example.mvvmkotlinlogin.model.Category

class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var cList = mutableListOf<Category>()
    @SuppressLint("NotifyDataSetChanged")
    fun setCategoryList(cList:List<Category>){
        this.cList = cList.toMutableList()
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: CategoryRvAdapterBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CategoryRvAdapterBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(cList[position]) {
                binding.category.text = this.title


                binding.productRecyclerView.layoutManager = GridLayoutManager(
                    binding.productRecyclerView.context,2,
                    RecyclerView.HORIZONTAL,false)

                binding.productRecyclerView.adapter = ProductAdapter(this.products)

                binding.subProduct.setOnClickListener {
                    if (binding.iconClose.visibility == View.VISIBLE){
                        binding.iconClose.visibility = View.GONE
                        binding.iconOpen.visibility = View.VISIBLE
                        binding.productRecyclerView.visibility = View.VISIBLE
                    } else {
                        binding.iconClose.visibility = View.VISIBLE
                        binding.iconOpen.visibility = View.GONE
                        binding.productRecyclerView.visibility = View.GONE

                    }

                }
            }
        }


    }

    override fun getItemCount(): Int {
        return cList.size
    }
}

