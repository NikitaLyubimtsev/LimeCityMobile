package ru.acts.webview.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import ru.acts.webview.R

class NotificationHelper(private val context: Context) {

    private val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(title: String, message: String) {
        val channelId = "LIME"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Lime", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setAutoCancel(true)

        notificationManager.notify(0, notificationBuilder.build())
    }

    fun cancelNotification() {
        notificationManager.cancel(0)
    }
}