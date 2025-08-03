package com.example.appmov_prod.Repository

import com.example.appmov_prod.Model.ChatMessage
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Repository class for handling chat-related data operations
 * Manages message storage and bot response generation
 */
class ChatRepository {
    
    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: Flow<List<ChatMessage>> = _messages.asStateFlow()
    
    private val predefinedResponses = mapOf(
        "hello" to "¡Hola! Soy tu asistente de reciclaje de Recyew. ¿En qué puedo ayudarte hoy?",
        "help" to "Puedo ayudarte con:\n• Información sobre reciclaje\n• Ubicación de puntos de reciclaje\n• Tipos de materiales reciclables\n• Consejos eco-amigables",
        "recycle" to "El reciclaje es fundamental para nuestro planeta. ¿Qué tipo de material quieres reciclar? Tenemos información sobre plástico, papel, vidrio y más.",
        "plastic" to "Para reciclar plástico:\n• Limpia los envases\n• Retira las etiquetas\n• Separa por tipo de plástico\n• Lleva a los puntos de reciclaje más cercanos",
        "paper" to "Para reciclar papel:\n• Evita papel sucio o húmedo\n• Retira grapas y clips\n• Separa cartón del papel\n• Deposita en contenedores azules",
        "location" to "Para encontrar puntos de reciclaje cerca de ti, usa la función de mapa en la app o dime tu ubicación.",
        "thanks" to "¡De nada! Estoy aquí para ayudarte a hacer un mundo más sostenible. ¿Hay algo más en lo que pueda asistirte?"
    )
    
    /**
     * Adds a user message to the conversation
     */
    suspend fun sendMessage(userMessage: String): Result<Unit> {
        return try {
            val message = ChatMessage(
                content = userMessage,
                isFromUser = true
            )
            
            val currentMessages = _messages.value.toMutableList()
            currentMessages.add(message)
            _messages.value = currentMessages
            
            // Generate bot response
            generateBotResponse(userMessage)
            
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    /**
     * Generates a bot response based on user input
     */
    private suspend fun generateBotResponse(userInput: String) {
        // Simulate typing delay
        delay(1000)
        
        val response = getBotResponse(userInput.lowercase().trim())
        val botMessage = ChatMessage(
            content = response,
            isFromUser = false
        )
        
        val currentMessages = _messages.value.toMutableList()
        currentMessages.add(botMessage)
        _messages.value = currentMessages
    }
    
    /**
     * Gets appropriate bot response based on user input
     */
    private fun getBotResponse(input: String): String {
        return when {
            input.contains("hola") || input.contains("hello") || input.contains("hi") -> 
                predefinedResponses["hello"]!!
            input.contains("ayuda") || input.contains("help") -> 
                predefinedResponses["help"]!!
            input.contains("recicl") -> 
                predefinedResponses["recycle"]!!
            input.contains("plastico") || input.contains("plastic") -> 
                predefinedResponses["plastic"]!!
            input.contains("papel") || input.contains("paper") -> 
                predefinedResponses["paper"]!!
            input.contains("ubicacion") || input.contains("location") || input.contains("donde") -> 
                predefinedResponses["location"]!!
            input.contains("gracias") || input.contains("thanks") -> 
                predefinedResponses["thanks"]!!
            else -> "Interesante pregunta. Como asistente de reciclaje, te recomiendo consultar nuestra sección de ayuda o pregúntame sobre tipos de materiales reciclables, ubicaciones de puntos de reciclaje o consejos eco-amigables."
        }
    }
    
    /**
     * Clears all messages from the conversation
     */
    fun clearMessages() {
        _messages.value = emptyList()
    }
    
    /**
     * Adds initial welcome message
     */
    fun initializeChat() {
        if (_messages.value.isEmpty()) {
            val welcomeMessage = ChatMessage(
                content = "¡Bienvenido al asistente de reciclaje de Recyew! 🌱\n\nEstoy aquí para ayudarte con todas tus dudas sobre reciclaje. Pregúntame lo que necesites.",
                isFromUser = false
            )
            _messages.value = listOf(welcomeMessage)
        }
    }
}
