package com.vitaliimalone.a10secondschat.domain.interactors

import com.vitaliimalone.a10secondschat.data.mappers.ChatEntityMapper
import com.vitaliimalone.a10secondschat.data.repository.ChatRepository
import com.vitaliimalone.a10secondschat.domain.mappers.ChatMapper
import com.vitaliimalone.a10secondschat.domain.models.Chat
import com.vitaliimalone.a10secondschat.domain.utils.applyIoSchedulers
import io.reactivex.Completable
import io.reactivex.Flowable

class HomeInteractor(
        private val chatRepository: ChatRepository
) {
    fun getAllChats(): Flowable<List<Chat>> {
        return chatRepository.getAllChats()
                .map(ChatMapper::map)
                .applyIoSchedulers()
    }

    fun saveChat(chat: Chat): Completable {
        return chatRepository.saveChat(ChatEntityMapper.map(chat))
                .applyIoSchedulers()
    }
}