package com.vitaliimalone.a10secondschat.domain.interactors

import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.vitaliimalone.a10secondschat.data.mappers.MessageEntityMapper
import com.vitaliimalone.a10secondschat.data.repository.ChatRepository
import com.vitaliimalone.a10secondschat.data.work.GenerateResponseWorker
import com.vitaliimalone.a10secondschat.domain.mappers.ChatMapper
import com.vitaliimalone.a10secondschat.domain.models.Chat
import com.vitaliimalone.a10secondschat.domain.utils.applyIoSchedulers
import io.reactivex.Completable
import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

class ChatInteractor(
        private val chatRepository: ChatRepository
) {
    fun getChat(chatId: String): Flowable<Chat> {
        return chatRepository.getChat(chatId)
                .map(ChatMapper::map)
                .applyIoSchedulers()
    }

    fun saveMessage(message: Chat.Message, chatId: String): Completable {
        return chatRepository.saveMessage(MessageEntityMapper.map(message, chatId))
                .applyIoSchedulers()
    }

    fun generateResponseWithDelay(chatId: String) {
        val inputData = Data.Builder()
                .putString(GenerateResponseWorker.CHAT_ID, chatId)
                .build()
        val generateResponseWork = OneTimeWorkRequestBuilder<GenerateResponseWorker>()
                .setInitialDelay(3, TimeUnit.SECONDS)
                .setInputData(inputData)
                .build()
        WorkManager.getInstance().enqueue(generateResponseWork)
    }
}