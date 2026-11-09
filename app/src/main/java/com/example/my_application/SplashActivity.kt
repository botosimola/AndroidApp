package com.example.my_application

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.example.my_application.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private val tag = "SplashActivity"
    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SPLASH_TIME_OUT = 2_000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = tag
        Log.d(tag, "onCreate: SplashActivity created")

        binding.logo.alpha = 0f
        binding.logo.scaleX = 0.85f
        binding.logo.scaleY = 0.85f
        binding.logo.animate().alpha(1f).scaleX(1f).scaleY(1f).setDuration(700L).start()
        binding.appName.animate().alpha(1f).setStartDelay(250L).setDuration(500L).start()
        binding.tagline.animate().alpha(1f).setStartDelay(400L).setDuration(500L).start()
        binding.description.animate().alpha(1f).setStartDelay(550L).setDuration(500L).start()

        handler.postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart: SplashActivity started")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume: SplashActivity resumed")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause: SplashActivity paused")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "onStop: SplashActivity stopped")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(tag, "onRestart: SplashActivity restarted")
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
        Log.d(tag, "onDestroy: SplashActivity destroyed")
    }
}