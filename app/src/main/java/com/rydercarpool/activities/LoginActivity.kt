package com.rydercarpool.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.rydercarpool.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("RyderCarpoolUsers", MODE_PRIVATE)

        binding.btnLogin.setOnClickListener {
            loginUser()
        }

        binding.tvSignUpRedirect.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }
    }

    private fun loginUser() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            showError("Please fill all fields")
            return
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showError("Please enter a valid email address")
            return
        }

        // Check if email exists
        if (!sharedPreferences.contains(email)) {
            showError("Email not found. Please sign up first.")
            return
        }

        // Verify password
        val userData = sharedPreferences.getString(email, "") ?: ""
        val parts = userData.split(":")

        if (parts.size < 2) {
            showError("Invalid user data. Please sign up again.")
            return
        }

        val storedPassword = parts[0]

        if (password != storedPassword) {
            showError("Invalid password")
            return
        }

        // Login successful - go to Dashboard
        Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, DashboardActivity::class.java)
        intent.putExtra("USER_EMAIL", email)
        startActivity(intent)
        finish()
    }

    private fun showError(message: String) {
        binding.tvError.text = message
        binding.tvError.visibility = android.view.View.VISIBLE
    }
}