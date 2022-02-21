package com.example.store.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.store.Repository.ProductRepository

class ProductViewModelFactory(private val repository: ProductRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        try {
            val constructor = modelClass.getDeclaredConstructor(ProductRepository::class.java)
            return constructor.newInstance(repository)
        } catch (e: Exception) {
           // loge(e.message.toString())
        }
        return super.create(modelClass)
    }

}