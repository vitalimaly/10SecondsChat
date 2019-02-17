package com.vitaliimalone.a10secondschat.data.repository.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.vitaliimalone.a10secondschat.data.repository.local.models.ChatAndMessages
import com.vitaliimalone.a10secondschat.data.repository.local.models.ChatEntity
import com.vitaliimalone.a10secondschat.data.repository.local.models.MessageEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface ChatDao {
    @Query("SELECT * FROM ChatEntity")
    fun getAllChats(): Flowable<List<ChatEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveChat(chat: ChatEntity): Completable

    @Transaction
    @Query("SELECT * FROM ChatEntity WHERE id = :chatId")
    fun getChatAndMessages(chatId: String): Flowable<ChatAndMessages>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMessage(message: MessageEntity): Completable
}