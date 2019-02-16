package com.vitaliimalone.a10secondschat.data.repository.local

import com.vitaliimalone.a10secondschat.data.repository.local.dao.ChatDao
import com.vitaliimalone.a10secondschat.data.repository.local.models.ChatEntity
import io.reactivex.Flowable

class ChatLocalDataSource(
        private val chatDao: ChatDao
) {
    fun getAllChats(): Flowable<List<ChatEntity>> {
        return chatDao.getAllChats()
    }
}