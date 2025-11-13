package com.rydercarpool.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.rydercarpool.R

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        findViewById<Button>(R.id.btnBookRide).setOnClickListener {
            val intent = Intent(this, BookRideActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnBeDriver).setOnClickListener {
            val intent = Intent(this, OfferRideActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnLogout).setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}