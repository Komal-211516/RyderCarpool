package com.rydercarpool.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.rydercarpool.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
    }

    private fun initializeViews() {
        val btnBookRide: Button = findViewById(R.id.btnBookRide)
        val btnRideHistory: Button = findViewById(R.id.btnRideHistory)
        val btnProfile: Button = findViewById(R.id.btnProfile)

        btnBookRide.setOnClickListener {
            startActivity(Intent(this, BookRideActivity::class.java))
        }

        btnRideHistory.setOnClickListener {
            startActivity(Intent(this, RideHistoryActivity::class.java))
        }

        btnProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }
}
