package com.example.store.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.store.Database.ProductDatabase
import com.example.store.Model.ProductList
import com.example.store.R
import com.example.store.Repository.ProductRepository
import com.example.store.ViewModel.ProductViewModel
import com.example.store.ViewModel.ProductViewModelFactory
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    lateinit var viewmodel: ProductViewModel
    lateinit var database: ProductDatabase
    private lateinit var repository: ProductRepository
    private lateinit var factory: ProductViewModelFactory
    var id=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        database = ProductDatabase(this)
        repository = ProductRepository(database)
        factory = ProductViewModelFactory(repository)
        viewmodel = ViewModelProvider(this, factory)[ProductViewModel::class.java]
      id=intent.getIntExtra("id",0)
       ShowDetailDatas(id)



    }

    private fun ShowDetailDatas(id: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            viewmodel.detailproduct(id).observe(this@DetailActivity, { items ->


            })
        }
    }
}


