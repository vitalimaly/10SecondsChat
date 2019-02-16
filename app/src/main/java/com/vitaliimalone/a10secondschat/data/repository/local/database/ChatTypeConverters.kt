package com.vitaliimalone.a10secondschat.data.repository.local.database

import androidx.room.TypeConverter
import com.vitaliimalone.a10secondschat.domain.models.Chat

class ChatTypeConverters {
    @TypeConverter
    fun chatMessageTypeToOrdinal(messageType: Chat.Message.MessageType): Int {
        return messageType.ordinal
    }

    @TypeConverter
    fun ordinalToMessageType(ordinal: Int): Chat.Message.MessageType {
        return Chat.Message.MessageType.values()[ordinal]
    }
}