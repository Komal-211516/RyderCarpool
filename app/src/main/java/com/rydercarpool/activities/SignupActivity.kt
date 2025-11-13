package com.rydercarpool.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.rydercarpool.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("RyderCarpoolUsers", MODE_PRIVATE)

        binding.btnRegister.setOnClickListener {
            registerUser()
        }

        binding.tvLoginRedirect.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun registerUser() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        val guardianNumber = binding.etGuardianNumber.text.toString().trim()

        // Validation
        if (email.isEmpty() || password.isEmpty() || guardianNumber.isEmpty()) {
            showError("Please fill all fields")
            return
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showError("Please enter a valid email")
            return
        }

        if (password.length < 6) {
            showError("Password must be at least 6 characters")
            return
        }

        // Check if email already exists
        if (sharedPreferences.contains(email)) {
            showError("This email already exists. Try login.")
            return
        }

        // Save user data (password:guardianNumber)
        val editor = sharedPreferences.edit()
        val userData = "$password:$guardianNumber"
        editor.putString(email, userData)
        editor.apply()

        Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun showError(message: String) {
        binding.tvError.text = message
        binding.tvError.visibility = android.view.View.VISIBLE
    }
}