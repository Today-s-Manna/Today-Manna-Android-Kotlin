package com.example.todaysmannanative

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.todaysmannanative.dataManagers.MannaManager
import com.example.todaysmannanative.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_mccheyne, R.id.navigation_memo
            )
        )
        binding.navView.setupWithNavController(findNavController(R.id.nav_host_fragment_activity_main))

        MannaManager.getData()
    }
}
