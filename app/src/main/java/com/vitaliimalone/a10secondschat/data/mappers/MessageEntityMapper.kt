package com.vitaliimalone.a10secondschat.data.mappers

import com.vitaliimalone.a10secondschat.data.repository.local.models.MessageEntity
import com.vitaliimalone.a10secondschat.domain.models.Chat

object MessageEntityMapper {
    fun map(model: Chat.Message, chatId: String): MessageEntity {
        return MessageEntity(model.id, chatId, model.time, model.text, model.type)
    }

}