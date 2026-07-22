package com.honorassistant.app.ui.hero.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.honorassistant.app.databinding.ItemHeroBinding
import com.honorassistant.app.data.models.Hero

class HeroAdapter(private val onItemClick: (Hero) -> Unit) : RecyclerView.Adapter<HeroAdapter.HeroViewHolder>() {
    private var heroes: List<Hero> = emptyList()
    fun submitList(list: List<Hero>) { heroes = list; notifyDataSetChanged() }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val binding = ItemHeroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HeroViewHolder(binding)
    }
    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        val hero = heroes[position]
        holder.bind(hero)
        holder.itemView.setOnClickListener { onItemClick(hero) }
    }
    override fun getItemCount(): Int = heroes.size
    class HeroViewHolder(private val binding: ItemHeroBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(hero: Hero) {
            binding.tvName.text = hero.name
            binding.tvTitle.text = hero.title
            Glide.with(binding.root).load(hero.splashArtUrl).placeholder(android.R.drawable.ic_menu_gallery).centerCrop().into(binding.ivSplash)
        }
    }
}
