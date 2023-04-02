package com.umut.shoppingapplication.adapters.orders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.umut.shoppingapplication.R
import com.umut.shoppingapplication.databinding.OrderItemBinding
import com.umut.shoppingapplication.models.Product

class OrdersRecyclerViewAdapter(private val orderItemClickListener: OrderItemClickListener) :
    RecyclerView.Adapter<OrdersRecyclerViewAdapter.OrdersViewHolder>() {

    class OrdersViewHolder(var view: OrderItemBinding) : RecyclerView.ViewHolder(view.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<OrderItemBinding>(
            inflater,
            R.layout.product_item,
            parent,
            false
        )
        return OrdersViewHolder(view)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
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