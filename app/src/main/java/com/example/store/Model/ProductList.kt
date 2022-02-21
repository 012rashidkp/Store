package com.example.store.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productinfo")
data class ProductList(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id : Int = 0,
    @ColumnInfo(name = "productName") val productName:String,
    @ColumnInfo(name = "productPrice") val productPrice:String,
    @ColumnInfo(name = "productImage") val productImage:String,
    @ColumnInfo(name = "availability") val availability:Boolean
    )
