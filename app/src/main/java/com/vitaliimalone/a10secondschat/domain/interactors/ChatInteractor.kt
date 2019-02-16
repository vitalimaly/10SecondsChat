package com.vitaliimalone.a10secondschat.domain.interactors

import com.vitaliimalone.a10secondschat.data.repository.ChatRepository
import com.vitaliimalone.a10secondschat.domain.mappers.ChatMapper
import com.vitaliimalone.a10secondschat.domain.models.Chat
import io.reactivex.Flowable

class ChatInteractor(
        private val chatRepository: ChatRepository
) {
    fun getAllChats(): Flowable<List<Chat>> {
        return chatRepository.getAllChats()
                .map(ChatMapper::map)
    }
}