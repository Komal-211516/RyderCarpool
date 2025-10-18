package com.rydercarpool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast

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
            val name = editTextName.text.toString()
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            
            // Get selected user type
            val selectedUserTypeId = radioGroupUserType.checkedRadioButtonId
            var userType = ""
            
            when (selectedUserTypeId) {
                R.id.radioDriver -> userType = "Driver"
                R.id.radioPassenger -> userType = "Passenger"
            }
            
            // Basic validation
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || userType.isEmpty()) {
                Toast.makeText(this, "Please fill all fields and select user type", Toast.LENGTH_SHORT).show()
            } else {
                // Success - show confirmation and navigate back to login
                Toast.makeText(this, "Signup successful! Welcome $name ($userType)", Toast.LENGTH_LONG).show()
                
                // Navigate back to login screen
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish() // Close signup activity
            }
        }
    }
}
