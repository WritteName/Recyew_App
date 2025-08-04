package com.example.appmov_prod.View.Chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.appmov_prod.Model.ChatMessage
import com.example.appmov_prod.R
import java.text.SimpleDateFormat
import java.util.*

/**
 * RecyclerView adapter for displaying chat messages
 * Handles both user and bot messages with different layouts
 */
class ChatAdapter : ListAdapter<ChatMessage, ChatAdapter.MessageViewHolder>(MessageDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chat_message, parent, false)
        return MessageViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val currentMessage = getItem(position)
        val previousMessage = if (position > 0) getItem(position - 1) else null
        holder.bind(currentMessage, previousMessage)
    }
    
    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val layoutUserMessage: LinearLayout = itemView.findViewById(R.id.layoutUserMessage)
        private val layoutBotMessage: LinearLayout = itemView.findViewById(R.id.layoutBotMessage)
        private val tvUserMessage: TextView = itemView.findViewById(R.id.tvUserMessage)
        private val tvBotMessage: TextView = itemView.findViewById(R.id.tvBotMessage)
        private val tvTimestamp: TextView = itemView.findViewById(R.id.tvTimestamp)
        
        private val timeFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())
        
        fun bind(message: ChatMessage, previousMessage: ChatMessage?) {
            if (message.isFromUser) {
                // Show user message layout
                layoutUserMessage.visibility = View.VISIBLE
                layoutBotMessage.visibility = View.GONE
                tvUserMessage.text = message.content
            } else {
                // Show bot message layout
                layoutUserMessage.visibility = View.GONE
                layoutBotMessage.visibility = View.VISIBLE
                tvBotMessage.text = message.content
            }
            
            // Handle timestamp visibility
            val currentTime = timeFormatter.format(Date(message.timestamp))
            val previousTime = previousMessage?.let { timeFormatter.format(Date(it.timestamp)) }
            
            // Show timestamp only if it's different from the previous message or if it's the first message
            if (previousMessage == null || currentTime != previousTime) {
                tvTimestamp.visibility = View.VISIBLE
                tvTimestamp.text = currentTime
            } else {
                tvTimestamp.visibility = View.GONE
            }
        }
    }
    
    class MessageDiffCallback : DiffUtil.ItemCallback<ChatMessage>() {
        override fun areItemsTheSame(oldItem: ChatMessage, newItem: ChatMessage): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: ChatMessage, newItem: ChatMessage): Boolean {
            return oldItem == newItem
        }
    }
}
