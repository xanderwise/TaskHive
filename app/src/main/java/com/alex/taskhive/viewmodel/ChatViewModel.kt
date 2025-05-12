package com.alex.taskhive.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.alex.taskhive.model.Message
import java.text.SimpleDateFormat
import java.util.*

class ChatViewModel : ViewModel() {

    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages: StateFlow<List<Message>> = _messages

    fun sendMessage(content: String) {
        val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
        val newMessage = Message(
            id = _messages.value.size + 1,
            sender = "You",
            content = content,
            timestamp = currentTime
        )
        _messages.value = _messages.value + newMessage
    }
}