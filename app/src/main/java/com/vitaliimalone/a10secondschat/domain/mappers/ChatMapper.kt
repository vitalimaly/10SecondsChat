package com.vitaliimalone.a10secondschat.domain.mappers

import com.vitaliimalone.a10secondschat.data.repository.local.models.ChatEntity
import com.vitaliimalone.a10secondschat.domain.models.Chat

object ChatMapper {
    fun map(entity: List<ChatEntity>): List<Chat> {
        return entity.map { Chat(it.id, it.title) }
    }
}