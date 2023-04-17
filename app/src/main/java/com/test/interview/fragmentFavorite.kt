package com.test.interview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.test.interview.databinding.FragmentFavoriteBinding
import com.test.interview.recievers.Notificationutils



class fragmentFavorite : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var notificationUtils: Notificationutils

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.repeatNotificationButton.setOnClickListener {

        }

        binding.dailyNotificationButton.setOnClickListener {

            notificationUtils = Notificationutils(requireContext())
            notificationUtils.scheduleNotification("02:51 PM")

            notificationUtils.createNotification(
                "My Notification",
                "This is a notification from My App",
                "10:00"
            )

        }

    }
}

