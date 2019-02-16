package com.vitaliimalone.a10secondschat.data.repository.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.vitaliimalone.a10secondschat.data.repository.local.models.ChatEntity
import io.reactivex.Flowable

@Dao
interface ChatDao {
    @Query("SELECT * FROM ChatEntity")
    fun getAllChats(): Flowable<List<ChatEntity>>
}