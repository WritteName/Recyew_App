package com.example.appmov_prod.View.Chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appmov_prod.R
import com.google.android.material.chip.Chip

/**
 * Adapter for quick reply suggestions in the chat
 */
class QuickReplyAdapter(
    private val quickReplies: List<String>,
    private val onQuickReplyClick: (String) -> Unit
) : RecyclerView.Adapter<QuickReplyAdapter.QuickReplyViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuickReplyViewHolder {
        val chip = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_quick_reply, parent, false) as Chip
        return QuickReplyViewHolder(chip)
    }
    
    override fun onBindViewHolder(holder: QuickReplyViewHolder, position: Int) {
        holder.bind(quickReplies[position])
    }
    
    override fun getItemCount(): Int = quickReplies.size
    
    inner class QuickReplyViewHolder(private val chip: Chip) : RecyclerView.ViewHolder(chip) {
        
        fun bind(quickReply: String) {
            chip.text = quickReply
            chip.setOnClickListener {
                onQuickReplyClick(quickReply)
            }
        }
    }
}
