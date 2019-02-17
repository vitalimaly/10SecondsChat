package com.vitaliimalone.a10secondschat.domain.interactors

import com.vitaliimalone.a10secondschat.data.mappers.MessageEntityMapper
import com.vitaliimalone.a10secondschat.data.repository.ChatRepository
import com.vitaliimalone.a10secondschat.domain.mappers.ChatMapper
import com.vitaliimalone.a10secondschat.domain.models.Chat
import com.vitaliimalone.a10secondschat.domain.utils.applyIoSchedulers
import io.reactivex.Completable
import io.reactivex.Flowable

class ChatInteractor(
        private val chatRepository: ChatRepository
) {
    fun getChat(chatId: String): Flowable<Chat> {
        return chatRepository.getChat(chatId)
                .map(ChatMapper::map)
                .applyIoSchedulers()
    }

    fun saveMessage(message: Chat.Message, chatId: String): Completable {
        return chatRepository.saveMessage(MessageEntityMapper.map(message, chatId))
                .applyIoSchedulers()
    }
}