package com.honorassistant.app.ui.buildsimulator.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.honorassistant.app.R
import com.honorassistant.app.data.models.Item

class ItemAdapter(private val onDragStart: (Item) -> Unit) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    private var items = emptyList<Item>()
    fun submitList(list: List<Item>) { items = list; notifyDataSetChanged() }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_build_item, parent, false) as ImageView
        return ItemViewHolder(view)
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        Glide.with(holder.itemView).load(item.iconUrl).placeholder(R.drawable.ic_item_placeholder).into(holder.itemView as ImageView)
        holder.itemView.setOnLongClickListener { onDragStart(item); true }
    }
    override fun getItemCount(): Int = items.size
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
