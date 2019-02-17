package com.vitaliimalone.a10secondschat.data.repository.local

import com.vitaliimalone.a10secondschat.data.repository.local.dao.ChatDao
import com.vitaliimalone.a10secondschat.data.repository.local.models.ChatAndMessages
import com.vitaliimalone.a10secondschat.data.repository.local.models.ChatEntity
import com.vitaliimalone.a10secondschat.data.repository.local.models.MessageEntity
import io.reactivex.Completable
import io.reactivex.Flowable

class ChatLocalDataSource(
        private val chatDao: ChatDao
) {
    fun getAllChats(): Flowable<List<ChatEntity>> {
        return chatDao.getAllChats()
    }

    fun saveChat(chat: ChatEntity): Completable {
        return chatDao.saveChat(chat)
    }

    fun getChat(chatId: String): Flowable<ChatAndMessages> {
        return chatDao.getChatAndMessages(chatId)
    }

    fun saveMessage(message: MessageEntity): Completable {
        return chatDao.saveMessage(message)
    }

    fun deleteChat(chat: ChatEntity): Completable {
        return chatDao.deleteChat(chat)
    }
}