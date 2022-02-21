package com.example.store.Repository

import com.example.store.Database.ProductDatabase
import com.example.store.Model.ProductList

class ProductRepository(private val database:ProductDatabase) {


    suspend fun insert(item: ProductList) = database.getProductDao().insertproduct(item)
    suspend fun delete(item: ProductList) = database.getProductDao().deleteproduct(item)
    suspend fun update(item: ProductList) = database.getProductDao().updateproduct(item)
    fun allProductinfo() = database.getProductDao().getAllProductInfo()
    fun detailproductinfo(prodid:Int)=database.getProductDao().getdetailproductinfo(prodid)
}