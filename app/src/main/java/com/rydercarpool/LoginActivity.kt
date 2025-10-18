package com.rydercarpool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        
        val btnGoToSignup = findViewById<Button>(R.id.btnGoToSignup)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        
        // Set up navigation to Signup screen
        btnGoToSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
        
        // Set up login functionality
        btnLogin.setOnClickListener {
            // For now, just show a message
            // You can add actual login logic later
            Toast.makeText(this, "Login button clicked!", Toast.LENGTH_SHORT).show()
            
            // If login is successful, navigate to main screen:
            // val intent = Intent(this, MainActivity::class.java)
            // startActivity(intent)
            // finish()
        }
    }
}
