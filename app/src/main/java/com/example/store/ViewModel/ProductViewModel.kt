package com.example.store.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.store.Model.ProductList
import com.example.store.Repository.ProductRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

 class ProductViewModel(private val repository: ProductRepository) :ViewModel() {
    // In coroutines thread insert item in insert function.
   suspend fun insert(item: ProductList) = GlobalScope.launch {
        repository.insert(item)
    }

    // In coroutines thread delete item in delete function.
  suspend  fun delete(item: ProductList) = GlobalScope.launch {
        repository.delete(item)
    }
 suspend fun update(item: ProductList) = GlobalScope.launch {
    repository.update(item)
}
    //Here we initialized allGroceryItems function with repository
    fun allProductinfo() = repository.allProductinfo()

    fun detailproduct(prodid:Int)=repository.detailproductinfo(prodid)
}