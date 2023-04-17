package com.test.interview.recievers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.test.interview.R
import java.util.*

class NotificationReciever : BroadcastReceiver() {

    private var tts: TextToSpeech? = null
    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationChannelId: String
    private lateinit var notificationChannelName: String

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            "PLAY_ACTION" -> {
                playSoundWithTTS(context.applicationContext, "Happy Ramazan")
            }
            "CANCEL_ACTION" -> {
                val notificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.cancel(1)
            }

        }

    }

    private fun playSoundWithTTS(context: Context, message: String) {
        tts = TextToSpeech(context.applicationContext, TextToSpeech.OnInitListener {
            if (it == TextToSpeech.SUCCESS) {
                tts!!.language = Locale.ENGLISH
                tts!!.speak(message, TextToSpeech.QUEUE_FLUSH, null)
            }
        })
    }

}


