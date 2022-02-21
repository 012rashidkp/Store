package com.example.store.Activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.store.Database.ProductDatabase
import com.example.store.Model.ProductList
import com.example.store.R
import com.example.store.Repository.ProductRepository
import com.example.store.ViewModel.ProductViewModel
import com.example.store.ViewModel.ProductViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ProductViewModel
    private lateinit var database: ProductDatabase
    private lateinit var repository: ProductRepository
    private lateinit var factory: ProductViewModelFactory



     private var productImage=""
    val REQUEST_CODE = 100
    //lateinit var snackbar: Snackbar
    lateinit var viewmodel: ProductViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = ProductDatabase(this)
        repository = ProductRepository(database)
        factory = ProductViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[ProductViewModel::class.java]
        imagepicker.setOnClickListener { OpenGallery() }
        showbtn.setOnClickListener {
            val intent=Intent(this, ShowActivity::class.java)
            startActivity(intent)
        }

    }

    private fun OpenGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){
            prodimg.setImageURI(data?.data) // handle chosen image
        }
        val uri:Uri=data?.data!!
        productImage=uri.toString()

        uploadprod.setOnClickListener {

            Uploadinfo() }
    }

    private fun Uploadinfo() {
        val productName=input_pr_name.text.toString()
        val productPrice=input_pr_price.text.toString()
        val availability=true
        Validate(productName, productPrice)
        if (Validate(productName, productPrice)){
            uploadtxt.visibility=View.GONE
            progressbar.visibility=View.VISIBLE
            val info=ProductList(0, productName, productPrice, productImage, availability)
            //  viewmodel.insert(info)
            CoroutineScope(Dispatchers.Main).launch {
                viewModel.insert(info).also {
               Loading()


                }
            }
        }



    }

    private fun Validate(productName: String, productPrice: String):Boolean {
     if (productName.equals("")){
         input_pr_name.setError("please enter product name")
         return false
     }
        else if (productPrice.equals("")){
            input_pr_price.setError("please enter price")
         return false
     }
        return true
    }

    fun DisplayMessage(view: View, message: String){
        Snackbar.make(view, message,
                Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }
fun Loading(){
    Handler().postDelayed({
        progressbar.visibility=View.GONE
        uploadtxt.visibility=View.VISIBLE

        DisplayMessage(msglayout, "saved")

    }, 2700)

}
}