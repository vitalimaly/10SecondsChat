package com.vitaliimalone.a10secondschat.data.repository

import com.vitaliimalone.a10secondschat.data.repository.local.ChatLocalDataSource
import com.vitaliimalone.a10secondschat.data.repository.local.models.ChatAndMessages
import com.vitaliimalone.a10secondschat.data.repository.local.models.ChatEntity
import com.vitaliimalone.a10secondschat.data.repository.local.models.MessageEntity
import io.reactivex.Completable
import io.reactivex.Flowable

class ChatRepository(
        private val chatLocalDataSource: ChatLocalDataSource
) {
    fun getAllChats(): Flowable<List<ChatEntity>> {
        return chatLocalDataSource.getAllChats()
    }

    fun saveChat(chat: ChatEntity): Completable {
        return chatLocalDataSource.saveChat(chat)
    }

    fun getChat(chatId: String): Flowable<ChatAndMessages> {
        return chatLocalDataSource.getChat(chatId)
    }

    fun saveMessage(message: MessageEntity): Completable {
        return chatLocalDataSource.saveMessage(message)
    }

    fun deleteChat(chat: ChatEntity): Completable {
        return chatLocalDataSource.deleteChat(chat)
    }
}