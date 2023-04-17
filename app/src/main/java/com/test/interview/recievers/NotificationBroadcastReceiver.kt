package com.test.interview.recievers


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


class NotificationBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val notificationUtils = Notificationutils(context!!)
        notificationUtils.createNotification(
            "My Notification",
            "This is a notification from My App",
            "10:00"
        )
    }
}

