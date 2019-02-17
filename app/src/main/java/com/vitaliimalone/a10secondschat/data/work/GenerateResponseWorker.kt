package com.vitaliimalone.a10secondschat.data.work

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.vitaliimalone.a10secondschat.data.mappers.MessageEntityMapper
import com.vitaliimalone.a10secondschat.data.notifications.Notificator
import com.vitaliimalone.a10secondschat.data.repository.ChatRepository
import com.vitaliimalone.a10secondschat.domain.models.Chat
import com.vitaliimalone.a10secondschat.presentation.utils.getRandomString
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class GenerateResponseWorker(
        context: Context, params: WorkerParameters
) : Worker(context, params), KoinComponent {
    companion object {
        const val CHAT_ID = "CHAT_ID"
    }

    private val chatRepository: ChatRepository by inject()
    private val notificator: Notificator by inject()

    override fun doWork(): Result {
        val chatId = inputData.getString(CHAT_ID) ?: return Result.failure()
        val message = Chat.Message(text = getRandomString(), type = Chat.Message.MessageType.RECEIVED)
        val messageEntity = MessageEntityMapper.map(message, chatId)
        val throwable = chatRepository.saveMessage(messageEntity).blockingGet()
        if (throwable != null) {
            Log.e(GenerateResponseWorker::class.java.simpleName, throwable.message, throwable)
            return Result.failure()
        }
        notificator.showMessageNotification(message)
        return Result.success()
    }
}