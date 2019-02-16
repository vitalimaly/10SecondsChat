package com.vitaliimalone.a10secondschat.domain.models

import java.util.*

data class Chat(
        val id: String = UUID.randomUUID().toString(),
        var title: String,
        var messages: List<Message> = emptyList()
) {
    data class Message(
            val id: String = UUID.randomUUID().toString(),
            var time: Long,
            var text: String,
            var type: MessageType
    ) {
        enum class MessageType { MY, RESPONSE }
    }
}