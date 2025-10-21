package com.rydercarpool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.rydercarpool.activities.LiveLocationActivity
import com.rydercarpool.activities.RatingActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find buttons using your EXACT IDs from the XML
        val loginButton = findViewById<Button>(R.id.loginButton)
        val signupButton = findViewById<Button>(R.id.signupButton)
        val safetyButton = findViewById<Button>(R.id.safetyButton)

        // Set up click listeners
        loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        signupButton.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        safetyButton.setOnClickListener {
            val intent = Intent(this, LiveLocationActivity::class.java)
            startActivity(intent)
        }

        Toast.makeText(this, "MainActivity loaded successfully!", Toast.LENGTH_SHORT).show()
    }
}
