package com.honorassistant.app.ui.buildsimulator.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.honorassistant.app.data.models.Item
import com.honorassistant.app.databinding.ItemBuildItemBinding

class ItemAdapter(private val onItemClick: (Item) -> Unit) :
    ListAdapter<Item, ItemAdapter.ItemViewHolder>(DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemBuildItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemViewHolder(private val binding: ItemBuildItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            binding.tvItemName.text = item.name
            binding.tvItemPrice.text = "${item.price}金"
            binding.root.setOnClickListener { onItemClick(item) }
        }
    }

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem
        }
    }
}
