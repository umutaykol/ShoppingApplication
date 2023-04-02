package com.umut.shoppingapplication.adapters.orders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.umut.shoppingapplication.R
import com.umut.shoppingapplication.databinding.OrderItemBinding
import com.umut.shoppingapplication.models.Order

class OrdersRecyclerViewAdapter(private val orderItemClickListener: OrderItemClickListener) :
    RecyclerView.Adapter<OrdersRecyclerViewAdapter.OrdersViewHolder>() {

    class OrdersViewHolder(var view: OrderItemBinding) : RecyclerView.ViewHolder(view.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<OrderItemBinding>(
            inflater,
            R.layout.order_item,
            parent,
            false
        )
        return OrdersViewHolder(view)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        holder.view.position = position
        holder.view.order = differ.currentList[position]
        holder.view.onClickListener = orderItemClickListener
    }

    private val differCallback = object : DiffUtil.ItemCallback<Order>() {
        override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

}