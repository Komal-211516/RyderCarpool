package com.rydercarpool.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rydercarpool.R
import com.rydercarpool.utils.SharedPreferencesHelper

class SignupActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPhone: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnSignup: Button
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        initializeViews()
        setupClickListeners()

        sharedPreferencesHelper = SharedPreferencesHelper(this)
    }

    private fun initializeViews() {
        etName = findViewById(R.id.editTextName)
        etEmail = findViewById(R.id.editTextEmail)
        etPhone = findViewById(R.id.editTextPhone)
        etPassword = findViewById(R.id.editTextPassword)
        btnSignup = findViewById(R.id.btnSignup)
    }

    private fun setupClickListeners() {
        btnSignup.setOnClickListener {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val phone = etPhone.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (validateInput(name, email, phone, password)) {
                // Save user data and login state
                sharedPreferencesHelper.saveLoginState(true)
                sharedPreferencesHelper.saveUserEmail(email)
                sharedPreferencesHelper.saveUserName(name)
                
                Toast.makeText(this, "Signup successful!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Please fill all fields correctly", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateInput(
        name: String,
        email: String,
        phone: String,
        password: String
    ): Boolean {
        return name.isNotEmpty() &&
                android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                phone.isNotEmpty() &&
                password.length >= 6
    }
}
