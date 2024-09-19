package com.onesignal.sample.android

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import com.onesignal.notifications.INotificationReceivedEvent
import com.onesignal.notifications.INotificationServiceExtension
import org.json.JSONObject
import java.util.logging.Logger

class NotificationServiceExtension : INotificationServiceExtension {

    companion object {
        private const val PROGRESS_LIVE_NOTIFICATION = "progress"
        private const val PROGRESS_CHANNEL_ID = "progress_channel"
        private val keyMap = mapOf(PROGRESS_LIVE_NOTIFICATION to 0)
        private val logger = Logger.getLogger(NotificationServiceExtension::class.java.name)
    }

    private var notificationChannelsCreated = false

    override fun onNotificationReceived(event: INotificationReceivedEvent) {
        val context = event.context
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
            ?: run {
                logger.warning("NotificationManager not available.")
                return
            }

        notificationManager.let {
            if (!notificationChannelsCreated) {
                createNotificationChannels(notificationManager)
            }
        }

        val additionalData = event.notification.additionalData
        val liveNotificationPayload = additionalData?.optJSONObject("live_notification")
        if (liveNotificationPayload == null) {
            logger.info("No live notification payload found. Showing original notification.")
            return
        }

        event.preventDefault()
        handleLiveNotification(event, liveNotificationPayload, notificationManager, context)
    }

    private fun handleLiveNotification(
        event: INotificationReceivedEvent,
        liveNotificationPayload: JSONObject,
        notificationManager: NotificationManager,
        context: Context
    ) {
        val liveNotificationKey = liveNotificationPayload.optString("key", "")
        val liveNotificationEvent = liveNotificationPayload.optString("event", "")
        val notificationId = keyMap[liveNotificationKey]

        if (liveNotificationEvent == "dismiss" && notificationId != null) {
            event.preventDefault()
            notificationManager.cancel(notificationId)
            logger.info("Notification dismissed with key: $liveNotificationKey")
            return
        }

        val liveNotificationUpdates = liveNotificationPayload.optJSONObject("event_updates") ?: run {
            logger.warning("Event updates not available for key: $liveNotificationKey")
            return
        }

        try {
            when (liveNotificationKey) {
                PROGRESS_LIVE_NOTIFICATION -> updateProgressNotification(liveNotificationUpdates, context, notificationManager)
                else -> logger.warning("Unsupported Live Notification Key provided: $liveNotificationKey")
            }
        } catch (e: Exception) {
            logger.severe("Error processing live notification: ${e.message}")
        }
    }

    private fun updateProgressNotification(
        liveNotificationUpdates: JSONObject,
        context: Context,
        notificationManager: NotificationManager
    ) {
        val currentProgress = liveNotificationUpdates.optInt("current_progress", 0)

        val builder = NotificationCompat.Builder(context, PROGRESS_CHANNEL_ID)
            .setContentTitle("Progress Live Notifications")
            .setContentText("It's working...")
            .setSmallIcon(android.R.drawable.ic_media_play)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources, android.R.drawable.ic_dialog_info))
            .setOngoing(true)
            .setOnlyAlertOnce(true)
            .setProgress(100, currentProgress, false)

        notificationManager.notify(keyMap[PROGRESS_LIVE_NOTIFICATION]!!, builder.build())
        logger.info("Updated progress notification with progress: $currentProgress")
    }

    private fun createNotificationChannels(notificationManager: NotificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (notificationManager.getNotificationChannel(PROGRESS_CHANNEL_ID) == null) {
                val progressChannel = NotificationChannel(
                    PROGRESS_CHANNEL_ID,
                    "Progress Live Notification",
                    NotificationManager.IMPORTANCE_LOW
                ).apply {
                    description = "Shows the progress of a download"
                }
                notificationManager.createNotificationChannel(progressChannel)
                notificationChannelsCreated = true
                logger.info("Notification channel created: $PROGRESS_CHANNEL_ID")
            }
        }
    }
}