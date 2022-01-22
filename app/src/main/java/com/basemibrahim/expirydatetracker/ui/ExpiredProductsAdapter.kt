package com.basemibrahim.expirydatetracker.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.basemibrahim.expirydatetracker.data.ExpiredProduct
import com.basemibrahim.expirydatetracker.data.Product
import com.basemibrahim.expirydatetracker.databinding.ExpiredProductViewItemBinding
import com.basemibrahim.expirydatetracker.databinding.GridViewItemBinding


class ExpiredProductsAdapter : ListAdapter<ExpiredProduct,
        ExpiredProductsAdapter.ViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExpiredProductsAdapter.ViewHolder {
        val binding = ExpiredProductViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ExpiredProductsAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(
        private var binding:
        ExpiredProductViewItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(expiredProduct: ExpiredProduct) {
            binding.expiredProduct = expiredProduct
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ExpiredProduct>() {
        override fun areItemsTheSame(oldItem: ExpiredProduct, newItem: ExpiredProduct): Boolean {
            return oldItem.expiryDate == newItem.expiryDate

        }

        override fun areContentsTheSame(oldItem: ExpiredProduct, newItem: ExpiredProduct): Boolean {
            return oldItem == newItem
        }
    }
}