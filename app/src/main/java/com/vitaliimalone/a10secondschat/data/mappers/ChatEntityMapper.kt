package com.vitaliimalone.a10secondschat.data.mappers

import com.vitaliimalone.a10secondschat.data.repository.local.models.ChatEntity
import com.vitaliimalone.a10secondschat.domain.models.Chat

object ChatEntityMapper {
    fun map(model: Chat): ChatEntity {
        return ChatEntity(model.id, model.title)
    }
}
