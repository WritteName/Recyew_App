package com.example.appmov_prod.View.Chat

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appmov_prod.R
import com.example.appmov_prod.ViewModel.ChatViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch

/**
 * Fragment for displaying the chatbot interface
 * Handles user interactions and displays chat messages
 */
class ChatFragment : Fragment(R.layout.fragment_chat) {
    
    private val viewModel: ChatViewModel by viewModels()
    
    // UI Components
    private lateinit var rvChatMessages: RecyclerView
    private lateinit var rvQuickReplies: RecyclerView
    private lateinit var layoutQuickReplies: LinearLayout
    private lateinit var tilMessageInput: TextInputLayout
    private lateinit var etMessageInput: TextInputEditText
    private lateinit var fabSendMessage: FloatingActionButton
    private lateinit var progressBarLoading: ProgressBar
    
    // Adapters
    private lateinit var chatAdapter: ChatAdapter
    private lateinit var quickReplyAdapter: QuickReplyAdapter
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        initViews(view)
        setupRecyclerViews()
        setupClickListeners()
        observeViewModel()
    }
    
    private fun initViews(view: View) {
        rvChatMessages = view.findViewById(R.id.rvChatMessages)
        rvQuickReplies = view.findViewById(R.id.rvQuickReplies)
        layoutQuickReplies = view.findViewById(R.id.layoutQuickReplies)
        tilMessageInput = view.findViewById(R.id.tilMessageInput)
        etMessageInput = view.findViewById(R.id.etMessageInput)
        fabSendMessage = view.findViewById(R.id.fabSendMessage)
        progressBarLoading = view.findViewById(R.id.progressBarLoading)
    }
    
    private fun setupRecyclerViews() {
        // Setup chat messages RecyclerView
        chatAdapter = ChatAdapter()
        rvChatMessages.apply {
            adapter = chatAdapter
            layoutManager = LinearLayoutManager(context).apply {
                stackFromEnd = true
            }
            // Add item decoration for better spacing
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: android.graphics.Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    outRect.bottom = 8
                }
            })
        }
        
        // Setup quick replies RecyclerView
        quickReplyAdapter = QuickReplyAdapter(
            quickReplies = viewModel.getQuickReplies(),
            onQuickReplyClick = { reply ->
                viewModel.sendQuickReply(reply)
                hideQuickReplies()
            }
        )
        rvQuickReplies.apply {
            adapter = quickReplyAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }
    
    private fun setupClickListeners() {
        fabSendMessage.setOnClickListener {
            sendMessage()
        }
        
        etMessageInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                sendMessage()
                true
            } else {
                false
            }
        }
    }
    
    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.messages.collect { messages ->
                chatAdapter.submitList(messages) {
                    if (messages.isNotEmpty()) {
                        rvChatMessages.smoothScrollToPosition(messages.size - 1)
                    }
                }
                
                // Show quick replies only when there are messages and not loading
                if (messages.isNotEmpty() && !viewModel.isLoading.value) {
                    showQuickReplies()
                }
            }
        }
        
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isLoading.collect { isLoading ->
                progressBarLoading.visibility = if (isLoading) View.VISIBLE else View.GONE
                fabSendMessage.isEnabled = !isLoading
                etMessageInput.isEnabled = !isLoading
            }
        }
        
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.errorMessage.collect { errorMessage ->
                errorMessage?.let {
                    Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                    viewModel.clearError()
                }
            }
        }
        
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.inputText.collect { text ->
                if (etMessageInput.text.toString() != text) {
                    etMessageInput.setText(text)
                }
            }
        }
    }
    
    private fun sendMessage() {
        val messageText = etMessageInput.text.toString().trim()
        if (messageText.isNotEmpty()) {
            viewModel.sendMessage(messageText)
            etMessageInput.text?.clear()
            hideQuickReplies()
        }
    }
    
    private fun showQuickReplies() {
        layoutQuickReplies.visibility = View.VISIBLE
    }
    
    private fun hideQuickReplies() {
        layoutQuickReplies.visibility = View.GONE
    }
    
    companion object {
        fun newInstance() = ChatFragment()
    }
}
