package com.example.appmov_prod.Model

import java.util.*

/**
 * Data class representing a chat message in the chatbot conversation
 * @param id Unique identifier for the message
 * @param content The text content of the message
 * @param isFromUser True if the message is from the user, false if from the bot
 * @param timestamp When the message was created
 * @param messageType Type of message (text, image, etc.) for future extensibility
 */
data class ChatMessage(
    val id: String = UUID.randomUUID().toString(),
    val content: String,
    val isFromUser: Boolean,
    val timestamp: Long = System.currentTimeMillis(),
    val messageType: MessageType = MessageType.TEXT
)

enum class MessageType {
    TEXT,
    IMAGE,
    QUICK_REPLY,
    TYPING_INDICATOR
}
