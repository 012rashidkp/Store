package com.example.store.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.store.Interface.ItemClickListener
import com.example.store.Model.ProductList
import com.example.store.R
import com.example.store.ViewModel.ProductViewModel

class ProductListAdapter(private val context: Context,var prodlist:List<ProductList>,private val viewModel: ProductViewModel,private val listener:ItemClickListener):RecyclerView.Adapter<ProductListAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductListAdapter.MyViewHolder {
val view=LayoutInflater.from(context).inflate(R.layout.productlist_row,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductListAdapter.MyViewHolder, position: Int) {
      val datas=prodlist[position]
        holder.txtname.text=datas.productName
        holder.txtprice.text=datas.productPrice
//        holder.img.load(datas.productImage)
        System.out.println("name "+datas.productName)
        holder.itemView.setOnClickListener { listener.ItemClick(datas.id) }
    }

    override fun getItemCount(): Int {
       return prodlist.size
    }
    class MyViewHolder(itemview: View):RecyclerView.ViewHolder(itemview) {
    val img=itemview.findViewById<ImageView>(R.id.prodimg)
    val txtname=itemview.findViewById<TextView>(R.id.prodname)
    val txtprice=itemview.findViewById<TextView>(R.id.prodprice)
    }

}