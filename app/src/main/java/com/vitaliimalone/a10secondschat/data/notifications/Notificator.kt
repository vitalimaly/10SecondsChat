package com.vitaliimalone.a10secondschat.data.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.vitaliimalone.a10secondschat.R
import com.vitaliimalone.a10secondschat.domain.models.Chat

class Notificator(private val context: Context) {
    companion object {
        private const val MESSAGES_CHANNEL_ID = "MESSAGES_CHANNEL_ID"
        private const val MESSAGES_CHANNEL_NAME = "Messages"
    }

    private val notificationManager = NotificationManagerCompat.from(context)

    init {
        createNotificationChannel(notificationManager)
    }

    private fun createNotificationChannel(notificationManager: NotificationManagerCompat) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(MESSAGES_CHANNEL_ID,
                    MESSAGES_CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = context.getString(R.string.notification_channel_description)
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun showMessageNotification(message: Chat.Message) {
        val notification = NotificationCompat.Builder(context, MESSAGES_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_message)
                .setContentTitle(context.getString(R.string.notification_default_title))
                .setContentText(message.text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .build()
        notificationManager.notify(message.time.toInt(), notification)
    }
}