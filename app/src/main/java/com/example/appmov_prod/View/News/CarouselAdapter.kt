package com.example.appmov_prod.View.News

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appmov_prod.Model.Content
import com.example.appmov_prod.databinding.ItemCarouselBinding

class CarouselAdapter(
    private val items: List<Content>,
    private val onItemClick: (Content) -> Unit
) : RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    inner class CarouselViewHolder(val binding: ItemCarouselBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCarouselBinding.inflate(inflater, parent, false)
        return CarouselViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        val item = items[position]
        holder.binding.title.text = item.title
        Glide.with(holder.itemView.context).load(item.imageUrl).into(holder.binding.image)
        holder.binding.root.setOnClickListener { onItemClick(item) }
    }

    override fun getItemCount(): Int = items.size
}