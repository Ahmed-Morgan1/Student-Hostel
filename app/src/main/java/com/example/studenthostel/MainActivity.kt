package com.example.studenthostel

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.example.studenthostel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        navController = findNavController(R.id.frame_layout)
        handleDesignationNavyHost()
    }

    private fun handleDesignationNavyHost() {
        navController.addOnDestinationChangedListener { _, navDestination: NavDestination, _ ->
            when (navDestination.id) {
                R.id.homeFragment -> {toggleBottomNav(true)}
                R.id.signupFragment2 -> {toggleBottomNav(false)}
            }

        }
    }

    private fun toggleBottomNav(show: Boolean) {
        binding.navigationHome.visibility = if (show) View.VISIBLE else View.GONE
    }
}