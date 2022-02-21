package com.example.store.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.store.Adapter.ProductListAdapter
import com.example.store.Database.ProductDatabase
import com.example.store.Interface.ItemClickListener
import com.example.store.R
import com.example.store.Repository.ProductRepository
import com.example.store.ViewModel.ProductViewModel
import com.example.store.ViewModel.ProductViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_show.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ShowActivity : AppCompatActivity() {
  //  lateinit var datas: List<ProductList>
   lateinit var viewmodel: ProductViewModel
     lateinit var database: ProductDatabase
    private lateinit var repository: ProductRepository
    private lateinit var factory: ProductViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        database = ProductDatabase(this)
        repository = ProductRepository(database)
        factory = ProductViewModelFactory(repository)
        viewmodel = ViewModelProvider(this, factory)[ProductViewModel::class.java]

        val madapter=ProductListAdapter(this@ShowActivity, listOf(),viewmodel,object :ItemClickListener{
            override fun ItemClick(id: Int) {
                DisplayMsg(showmsg,"item id $id")
                val intent=Intent(this@ShowActivity,DetailActivity::class.java)
                startActivity(intent)
            }

        })
        ShowDatas(madapter)
        listitems.layoutManager = LinearLayoutManager(this)
        listitems.adapter =madapter




    }

    private fun ShowDatas(madapter:ProductListAdapter) {
        CoroutineScope(Dispatchers.Main).launch {
            viewmodel.allProductinfo().observe(this@ShowActivity, { notes ->
                madapter.prodlist=notes
                madapter.notifyDataSetChanged()
            })
        }
    }
fun DisplayMsg(view:View,msg:String){
    Snackbar.make(view, msg,
            Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
}

   
}