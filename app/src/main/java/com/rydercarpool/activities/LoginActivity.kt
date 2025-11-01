package com.rydercarpool.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rydercarpool.R
import com.rydercarpool.utils.SharedPreferencesHelper

class LoginActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnGoToSignup: TextView
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initializeViews()
        setupClickListeners()

        sharedPreferencesHelper = SharedPreferencesHelper(this)
        
        // Check if user is already logged in
        if (sharedPreferencesHelper.getLoginState()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun initializeViews() {
        etEmail = findViewById(R.id.editTextEmail)
        etPassword = findViewById(R.id.editTextPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnGoToSignup = findViewById(R.id.btnGoToSignup)
    }

    private fun setupClickListeners() {
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (validateInput(email, password)) {
                // For demo purposes, accept any valid email and password >= 6 chars
                sharedPreferencesHelper.saveLoginState(true)
                sharedPreferencesHelper.saveUserEmail(email)
                
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Please enter valid email and password (min 6 characters)", Toast.LENGTH_SHORT).show()
            }
        }

        btnGoToSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

    private fun validateInput(email: String, password: String): Boolean {
        return email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length >= 6
    }
}