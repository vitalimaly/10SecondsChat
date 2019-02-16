package com.vitaliimalone.a10secondschat.data.repository.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vitaliimalone.a10secondschat.domain.models.Chat

@Entity
data class MessageEntity(
        @PrimaryKey
        var id: String,
        var chatId: String,
        var time: Long,
        var text: String,
        var type: Chat.Message.MessageType
)