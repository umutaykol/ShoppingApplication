package com.umut.shoppingapplication.adapters.orders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.umut.shoppingapplication.R
import com.umut.shoppingapplication.databinding.ProductItemBinding
import com.umut.shoppingapplication.models.Product

class OrdersRecyclerViewAdapter(private val orderItemClickListener: OrderItemClickListener) :
    RecyclerView.Adapter<OrdersRecyclerViewAdapter.NotesViewHolder>() {

    class NotesViewHolder(var view: ProductItemBinding) : RecyclerView.ViewHolder(view.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ProductItemBinding>(
            inflater,
            R.layout.product_item,
            parent,
            false
        )
        return NotesViewHolder(view)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.view.position = position
        holder.view.product = differ.currentList[position]
        holder.view.onClickListener = orderItemClickListener
    }

    private val differCallback = object : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

}