package com.test.interview

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.test.interview.databinding.FragmentRamzanBinding
import com.test.interview.databinding.FragmentSplashBinding
import com.test.interview.recievers.NotificationReciever

class RamzanFragment : Fragment() {

    private lateinit var binding: FragmentRamzanBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRamzanBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.showRamazanNotification.setOnClickListener {
            showNotification()
        }
    }


    private fun showNotification() {
        // Create an Intent for the play action
        val playIntent = Intent(context, NotificationReciever::class.java).apply {
            action = "PLAY_ACTION"
        }
        val pendingPlayIntent = PendingIntent.getBroadcast(
            context,
            0,
            playIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Create an Intent for the cancel action
        val cancelIntent = Intent(context, NotificationReciever::class.java).apply {
            action = "CANCEL_ACTION"
        }
        val pendingCancelIntent = PendingIntent.getBroadcast(
            context,
            0,
            cancelIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Create a notification channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "my_channel_id"
            val channelName = "My Channel"
            val channelDescription = "This is my notification channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName, importance)
            channel.description = channelDescription

            // Register the channel with the system
            val notificationManager =
                requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        // Show the notification
        val notificationId = 1
        val builder = NotificationCompat.Builder(requireContext(), "my_channel_id")
            .setContentTitle("Happy Ramazan")
            .setSmallIcon(R.drawable.ic_ramzan_icon)
            .setStyle(androidx.media.app.NotificationCompat.MediaStyle())
            .addAction(R.drawable.play_icon, "Play", pendingPlayIntent)
            .addAction(R.drawable.cancel_icon, "Cancel", pendingCancelIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(requireContext())) {
            notify(notificationId, builder.build())
        }
    }

}
