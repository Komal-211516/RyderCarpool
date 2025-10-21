package com.rydercarpool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import android.util.Patterns

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        
        val buttonSignup = findViewById<Button>(R.id.buttonSignup)
        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val editTextPassword = findViewById<EditText>(R.id.editTextPassword)
        val radioGroupUserType = findViewById<RadioGroup>(R.id.radioGroupUserType)
        
        buttonSignup.setOnClickListener {
            // Get user input
            val name = editTextName.text.toString().trim()
            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()
            
            // Get selected user type
            val selectedUserTypeId = radioGroupUserType.checkedRadioButtonId
            var userType = ""
            
            when (selectedUserTypeId) {
                R.id.radioDriver -> userType = "Driver"
                R.id.radioPassenger -> userType = "Passenger"
            }
            
            // Enhanced validation
            if (validateInputs(name, email, password, userType)) {
                // Success - show confirmation and navigate back to login
                Toast.makeText(this, "Signup successful! Welcome $name ($userType)", Toast.LENGTH_LONG).show()
                
                // Navigate back to login screen
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish() // Close signup activity
            }
        }
    }
    
    private fun validateInputs(name: String, email: String, password: String, userType: String): Boolean {
        if (name.isEmpty()) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            return false
        }
        
        if (email.isEmpty()) {
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show()
            return false
        }
        
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
            return false
        }
        
        if (password.isEmpty()) {
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show()
            return false
        }
        
        if (password.length < 6) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
            return false
        }
        
        if (userType.isEmpty()) {
            Toast.makeText(this, "Please select user type", Toast.LENGTH_SHORT).show()
            return false
        }
        
        return true
    }
}