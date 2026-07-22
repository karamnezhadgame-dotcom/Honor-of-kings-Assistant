package com.honorassistant.app.ui.lore.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.honorassistant.app.databinding.ItemLoreLocationBinding
import com.honorassistant.app.data.models.LoreLocation

class LoreAdapter(private val onItemClick: (LoreLocation) -> Unit) : RecyclerView.Adapter<LoreAdapter.LoreViewHolder>() {
    private var items: List<LoreLocation> = emptyList()
    fun submitList(list: List<LoreLocation>) { items = list; notifyDataSetChanged() }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoreViewHolder {
        val binding = ItemLoreLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoreViewHolder(binding)
    }
    override fun onBindViewHolder(holder: LoreViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.binding.btnExpandLore.setOnClickListener { onItemClick(item) }
    }
    override fun getItemCount(): Int = items.size
    class LoreViewHolder(val binding: ItemLoreLocationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: LoreLocation) {
            binding.tvLoreName.text = item.name
            binding.tvLoreDescription.text = item.description
            binding.tvLoreHeroes.text = "涉及英雄: ${item.associatedHeroes.joinToString(", ")}"
            Glide.with(binding.root).load(item.imageUrl).centerCrop().into(binding.ivLoreImage)
        }
    }
}
