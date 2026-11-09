package com.example.my_application

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.my_application.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = TAG
        Log.d(TAG, "onCreate: MainActivity created")
        //binding.textView.text = intent.getStringExtra("message")
        // Get NavController from NavHostFragment
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        // Define top-level destinations for ActionBar
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.profileFragment)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        // Hook up BottomNavigationView via binding
        binding.bottomNav.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNav.visibility = when (destination.id) {
                R.id.loginFragment, R.id.registerFragment -> View.GONE
                else -> View.VISIBLE
            }
        }
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: MainActivity started")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: MainActivity resumed")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: MainActivity paused")
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: MainActivity stopped")
    }
    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: MainActivity restarted")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: MainActivity destroyed")
    }
}