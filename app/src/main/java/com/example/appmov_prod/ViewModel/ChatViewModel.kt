package com.example.appmov_prod.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmov_prod.Model.ChatMessage
import com.example.appmov_prod.Repository.ChatRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for managing chat functionality
 * Handles message sending, state management, and UI updates
 */
class ChatViewModel(
    private val repository: ChatRepository = ChatRepository()
) : ViewModel() {
    
    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    
    private val _inputText = MutableStateFlow("")
    val inputText: StateFlow<String> = _inputText.asStateFlow()
    
    init {
        initializeChat()
        observeMessages()
    }
    
    /**
     * Initialize chat with welcome message
     */
    private fun initializeChat() {
        repository.initializeChat()
    }
    
    /**
     * Observe messages from repository
     */
    private fun observeMessages() {
        viewModelScope.launch {
            repository.messages.collect { messageList ->
                _messages.value = messageList
            }
        }
    }
    
    /**
     * Send a message from the user
     */
    fun sendMessage(message: String) {
        if (message.isBlank()) return
        
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            repository.sendMessage(message).fold(
                onSuccess = {
                    _inputText.value = ""
                },
                onFailure = { exception ->
                    _errorMessage.value = exception.message ?: "Error al enviar mensaje"
                }
            )
            
            _isLoading.value = false
        }
    }
    
    /**
     * Update input text
     */
    fun updateInputText(text: String) {
        _inputText.value = text
    }
    
    /**
     * Clear error message
     */
    fun clearError() {
        _errorMessage.value = null
    }
    
    /**
     * Clear all messages
     */
    fun clearChat() {
        repository.clearMessages()
        initializeChat()
    }
    
    /**
     * Send predefined quick reply
     */
    fun sendQuickReply(reply: String) {
        sendMessage(reply)
    }
    
    /**
     * Get quick reply options
     */
    fun getQuickReplies(): List<String> {
        return listOf(
            "¿Cómo reciclar plástico?",
            "Puntos de reciclaje cercanos",
            "Tipos de materiales",
            "Consejos eco-amigables"
        )
    }
}
