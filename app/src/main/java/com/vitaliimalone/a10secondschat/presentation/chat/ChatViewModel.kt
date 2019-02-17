package com.vitaliimalone.a10secondschat.presentation.chat

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.vitaliimalone.a10secondschat.domain.interactors.ChatInteractor
import com.vitaliimalone.a10secondschat.domain.models.Chat
import com.vitaliimalone.a10secondschat.presentation.MainRouter
import com.vitaliimalone.a10secondschat.presentation.base.BaseViewModel
import com.vitaliimalone.a10secondschat.presentation.utils.SingleLiveEvent

class ChatViewModel(
        private val mainRouter: MainRouter,
        private val chatInteractor: ChatInteractor
) : BaseViewModel() {
    val chat by lazy { MutableLiveData<Chat>() }
    val clearEditText by lazy { SingleLiveEvent<Unit>() }

    fun getChat(chatId: String) {
        autoDispose(chatInteractor.getChat(chatId)
                .subscribe({
                    chat.value = it
                }, {
                    Log.e(ChatViewModel::class.java.simpleName, it.message, it)
                }))
    }

    fun onSendMessageClick(text: String, chatId: String) {
        val message = Chat.Message(text = text, type = Chat.Message.MessageType.SENT)
        autoDispose(chatInteractor.saveMessage(message, chatId)
                .subscribe({
                    clearEditText.value = Unit
                    generateResponseWithDelay(chatId)
                }, {
                    Log.e(ChatViewModel::class.java.simpleName, it.message, it)
                }))
    }

    private fun generateResponseWithDelay(chatId: String) {
        chatInteractor.generateResponseWithDelay(chatId)
    }
}
