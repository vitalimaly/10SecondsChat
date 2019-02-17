package com.vitaliimalone.a10secondschat.domain.mappers

import com.vitaliimalone.a10secondschat.data.repository.local.models.ChatAndMessages
import com.vitaliimalone.a10secondschat.data.repository.local.models.ChatEntity
import com.vitaliimalone.a10secondschat.domain.models.Chat

object ChatMapper {
    fun map(entity: List<ChatEntity>): List<Chat> {
        return entity.map { Chat(it.id, it.title) }
    }

    fun map(entity: ChatAndMessages): Chat {
        return Chat(entity.chat.id, entity.chat.title, entity.messages.map {
            Chat.Message(it.id, it.time, it.text, it.type)
        })
    }
}