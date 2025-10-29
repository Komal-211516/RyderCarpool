package com.rydercarpool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.util.Patterns
import com.rydercarpool.activities.LiveLocationActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnGoToSignup: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnGoToSignup = findViewById(R.id.btnGoToSignup)

        btnLogin.setOnClickListener { attemptLogin() }

        btnGoToSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }

    private fun attemptLogin() {
        val email = editTextEmail.text.toString().trim()
        val password = editTextPassword.text.toString().trim()

        if (validateInputs(email, password)) {
            Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
            openSafetyFeatures()
        }
    }

    private fun validateInputs(email: String, password: String): Boolean {
        var valid = true

        if (email.isEmpty()) {
            editTextEmail.error = "Email is required"
            valid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.error = "Enter a valid email address"
            valid = false
        } else {
            editTextEmail.error = null
        }

        if (password.isEmpty()) {
            editTextPassword.error = "Password is required"
            valid = false
        } else if (password.length < 6) {
            editTextPassword.error = "Password must be at least 6 characters"
            valid = false
        } else {
            editTextPassword.error = null
        }

        return valid
    }

    private fun openSafetyFeatures() {
        val intent = Intent(this, LiveLocationActivity::class.java)
        startActivity(intent)
        finish()
    }
}
```// filepath: /workspaces/RyderCarpool/app/src/main/java/com/rydercarpool/LoginActivity.kt
package com.rydercarpool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.util.Patterns
import com.rydercarpool.activities.LiveLocationActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnGoToSignup: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnGoToSignup = findViewById(R.id.btnGoToSignup)

        btnLogin.setOnClickListener { attemptLogin() }

        btnGoToSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }

    private fun attemptLogin() {
        val email = editTextEmail.text.toString().trim()
        val password = editTextPassword.text.toString().trim()

        if (validateInputs(email, password)) {
            Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
            openSafetyFeatures()
        }
    }

    private fun validateInputs(email: String, password: String): Boolean {
        var valid = true

        if (email.isEmpty()) {
            editTextEmail.error = "Email is required"
            valid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.error = "Enter a valid email address"
            valid = false
        } else {
            editTextEmail.error = null
        }

        if (password.isEmpty()) {
            editTextPassword.error = "Password is required"
            valid = false
        } else if (password.length < 6) {
            editTextPassword.error = "Password must be at least 6 characters"
            valid = false
        } else {
            editTextPassword.error = null
        }

        return valid
    }

    private fun openSafetyFeatures() {
        val intent = Intent(this, LiveLocationActivity::class.java)
        startActivity(intent)
        finish()
    }
}