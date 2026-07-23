package com.honorassistant.app.ui.hero.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.honorassistant.app.data.models.Hero
import com.honorassistant.app.databinding.ItemHeroBinding

class HeroAdapter(private val onHeroClick: (Hero) -> Unit) :
    ListAdapter<Hero, HeroAdapter.HeroViewHolder>(DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val binding = ItemHeroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HeroViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class HeroViewHolder(private val binding: ItemHeroBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(hero: Hero) {
            binding.tvName.text = hero.name
            binding.tvTitle.text = hero.title
            Glide.with(binding.ivSplash)
                .load(hero.splashArtUrl)
                .placeholder(android.R.color.darker_gray)
                .centerCrop()
                .into(binding.ivSplash)
            binding.root.setOnClickListener { onHeroClick(hero) }
        }
    }

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<Hero>() {
            override fun areItemsTheSame(oldItem: Hero, newItem: Hero) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Hero, newItem: Hero) = oldItem == newItem
        }
    }
}
