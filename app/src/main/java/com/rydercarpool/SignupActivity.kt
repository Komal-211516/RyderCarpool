package com.rydercarpool

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val editTextName = findViewById<EditText?>(R.id.editTextName)
        val editTextEmail = findViewById<EditText?>(R.id.editTextEmail)
        val editTextPassword = findViewById<EditText?>(R.id.editTextPassword)
        val radioGroupUserType = findViewById<RadioGroup?>(R.id.radioGroupUserType)
        val buttonSignup = findViewById<Button?>(R.id.buttonSignup)

        buttonSignup?.setOnClickListener {
            val name = editTextName?.text?.toString()?.trim().orEmpty()
            val email = editTextEmail?.text?.toString()?.trim().orEmpty()
            val password = editTextPassword?.text?.toString().orEmpty()

            val selectedId = radioGroupUserType?.checkedRadioButtonId ?: -1
            val userType = when (selectedId) {
                R.id.radioDriver -> "Driver"
                R.id.radioPassenger -> "Passenger"
                else -> ""
            }

            if (validateInputs(name, email, password, userType)) {
                Toast.makeText(this, "Signup successful! Welcome $name ($userType)", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }

    private fun validateInputs(name: String, email: String, password: String, userType: String): Boolean {
        if (name.isBlank()) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            return false
        }

        if (email.isBlank()) {
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
```// filepath: /workspaces/RyderCarpool/app/src/main/java/com/rydercarpool/SignupActivity.kt
package com.rydercarpool

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val editTextName = findViewById<EditText?>(R.id.editTextName)
        val editTextEmail = findViewById<EditText?>(R.id.editTextEmail)
        val editTextPassword = findViewById<EditText?>(R.id.editTextPassword)
        val radioGroupUserType = findViewById<RadioGroup?>(R.id.radioGroupUserType)
        val buttonSignup = findViewById<Button?>(R.id.buttonSignup)

        buttonSignup?.setOnClickListener {
            val name = editTextName?.text?.toString()?.trim().orEmpty()
            val email = editTextEmail?.text?.toString()?.trim().orEmpty()
            val password = editTextPassword?.text?.toString().orEmpty()

            val selectedId = radioGroupUserType?.checkedRadioButtonId ?: -1
            val userType = when (selectedId) {
                R.id.radioDriver -> "Driver"
                R.id.radioPassenger -> "Passenger"
                else -> ""
            }

            if (validateInputs(name, email, password, userType)) {
                Toast.makeText(this, "Signup successful! Welcome $name ($userType)", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }

    private fun validateInputs(name: String, email: String, password: String, userType: String): Boolean {
        if (name.isBlank()) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            return false
        }

        if (email.isBlank()) {
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