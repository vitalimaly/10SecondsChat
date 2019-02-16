package com.vitaliimalone.a10secondschat.data.repository.local.models

import androidx.room.Embedded
import androidx.room.Relation

data class ChatAndMessages(
        @Embedded
        val chat: ChatEntity,
        @Relation(parentColumn = "id", entityColumn = "chatId")
        val messages: List<MessageEntity>
)
