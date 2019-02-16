package com.vitaliimalone.a10secondschat.data.repository.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vitaliimalone.a10secondschat.data.repository.local.dao.ChatDao
import com.vitaliimalone.a10secondschat.data.repository.local.models.ChatEntity
import com.vitaliimalone.a10secondschat.data.repository.local.models.MessageEntity

@Database(entities = [ChatEntity::class, MessageEntity::class], version = 1, exportSchema = false)
@TypeConverters(ChatTypeConverters::class)
abstract class ChatDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "chatDatabase"
    }

    abstract fun chatDao(): ChatDao
}