package com.test.interview

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.test.interview.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()

    }

    private fun setupNavigation() {
        navController = findNavController(R.id.fragment_container)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        navController.navigate(R.id.splashFragment2)

    }

    override fun onBackPressed() {
        if (onBackPressedDispatcher.hasEnabledCallbacks()) {
            super.onBackPressed()
        }


       /* val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setView(R.layout.exit_dialog)
        val alertDialog = dialogBuilder.create()
        alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.show()
        alertDialog.setCancelable(true)

        val exitBtn = alertDialog.findViewById(R.id.btnExit) as AppCompatButton
        val cancel = alertDialog.findViewById(R.id.btnCancel) as AppCompatButton
        val btnRate = alertDialog.findViewById(R.id.ratingBar) as RatingBar
        val rateComments = alertDialog.findViewById(R.id.ratingText) as TextView

        btnRate.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { ratingBar, rating, fromUser ->

                rateComments.visibility = View.VISIBLE

                var message: String? = null

                when (rating) {
                    0f -> {
                        rateComments.visibility = View.GONE
                    }
                    1f -> {
                        message = " Not Good "
                    }
                    2f -> {
                        message = " Quite Ok! "
                    }
                    3f -> {
                        message = " Very Good! "
                    }
                    4f -> {
                        message = " Excellent!! "
                    }
                    5f -> {
                        message = " Awesome! Thank You! :) "
                    }
                }
                rateComments.text = message

            }

        exitBtn.setOnClickListener {
            finishAffinity()
            alertDialog.dismiss()
        }

        cancel.setOnClickListener {
            alertDialog.dismiss()
        }
*/

    }
}
