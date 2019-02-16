package com.vitaliimalone.a10secondschat.data.repository.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ChatEntity(
        @PrimaryKey
        var id: String,
        var title: String
)
