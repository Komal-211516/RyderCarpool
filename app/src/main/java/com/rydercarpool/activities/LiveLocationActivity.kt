package com.rydercarpool.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.rydercarpool.databinding.ActivityLiveLocationBinding

class LiveLocationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLiveLocationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiveLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        // Use the actual button IDs from your layout
        // If these buttons don't exist, remove these click listeners
        // binding.btnStartLocation?.setOnClickListener {
        //     startLocationTracking()
        // }

        // binding.btnStopLocation?.setOnClickListener {
        //     stopLocationTracking()
        // }

        // For now, just show a message
        Toast.makeText(this, "Live Location feature coming soon", Toast.LENGTH_SHORT).show()
    }

    private fun startLocationTracking() {
        Toast.makeText(this, "Location tracking started", Toast.LENGTH_SHORT).show()
    }

    private fun stopLocationTracking() {
        Toast.makeText(this, "Location tracking stopped", Toast.LENGTH_SHORT).show()
    }
}