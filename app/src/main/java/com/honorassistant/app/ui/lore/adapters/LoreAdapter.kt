package com.honorassistant.app.ui.lore.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.honorassistant.app.data.models.LoreLocation
import com.honorassistant.app.databinding.ItemLoreLocationBinding

class LoreAdapter(private val onItemClick: (LoreLocation) -> Unit) :
    ListAdapter<LoreLocation, LoreAdapter.LoreViewHolder>(DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoreViewHolder {
        val binding = ItemLoreLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoreViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class LoreViewHolder(private val binding: ItemLoreLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(location: LoreLocation) {
            binding.tvLoreName.text = location.name
            binding.tvLoreDescription.text = location.description
            binding.tvLoreHeroes.text = "涉及英雄：${location.associatedHeroes.joinToString("、")}"
            Glide.with(binding.ivLoreImage)
                .load(location.imageUrl)
                .placeholder(android.R.color.darker_gray)
                .centerCrop()
                .into(binding.ivLoreImage)
            binding.root.setOnClickListener { onItemClick(location) }
        }
    }

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<LoreLocation>() {
            override fun areItemsTheSame(oldItem: LoreLocation, newItem: LoreLocation) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: LoreLocation, newItem: LoreLocation) = oldItem == newItem
        }
    }
}
