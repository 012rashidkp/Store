package com.example.store.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.store.Model.ProductList

@Dao
interface ProductDao {
    @Query("SELECT * FROM productinfo ORDER BY id DESC")
    fun getAllProductInfo(): LiveData<List<ProductList>>

   @Query("SELECT * FROM productinfo WHERE id= :prodid")
   fun getdetailproductinfo(prodid:Int):LiveData<List<ProductList>>

    @Insert
   suspend fun insertproduct(productList: ProductList?)

    @Delete
   suspend fun deleteproduct(productList: ProductList?)

    @Update
   suspend fun updateproduct(productList: ProductList?)
}