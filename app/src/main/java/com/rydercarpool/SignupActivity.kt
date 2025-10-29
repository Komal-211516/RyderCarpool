package com.rydercarpool

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class SignupActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var radioGroupUserType: RadioGroup
    private lateinit var buttonSignup: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // Firebase
        auth = Firebase.auth
        db = FirebaseFirestore.getInstance()

        // Views
        editTextName = findViewById(R.id.editTextName)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        radioGroupUserType = findViewById(R.id.radioGroupUserType)
        buttonSignup = findViewById(R.id.buttonSignup)

        buttonSignup.setOnClickListener {
            val name = editTextName.text.toString().trim()
            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString()

            val selectedId = radioGroupUserType.checkedRadioButtonId
            val userType = when (selectedId) {
                R.id.radioDriver -> "Driver"
                R.id.radioPassenger -> "Passenger"
                else -> ""
            }

            if (!validateInputs(name, email, password, userType)) return@setOnClickListener

            signupUser(name, email, password, userType)
        }
    }

    private fun validateInputs(name: String, email: String, password: String, userType: String): Boolean {
        if (name.isBlank()) {
            editTextName.error = "Name required"
            return false
        } else {
            editTextName.error = null
        }

        if (email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.error = "Valid email required"
            return false
        } else {
            editTextEmail.error = null
        }

        if (password.isEmpty() || password.length < 6) {
            editTextPassword.error = "Password must be at least 6 characters"
            return false
        } else {
            editTextPassword.error = null
        }

        if (userType.isEmpty()) {
            Toast.makeText(this, "Please select user type", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun signupUser(name: String, email: String, password: String, userType: String) {
        // Disable button to prevent double clicks (optional)
        buttonSignup.isEnabled = false

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                buttonSignup.isEnabled = true
                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid ?: ""
                    if (userId.isNotEmpty()) {
                        saveUserToFirestore(userId, name, email, userType)
                    } else {
                        Toast.makeText(this, "Signup succeeded but user id missing", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }
                } else {
                    Toast.makeText(this, "Signup failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun saveUserToFirestore(userId: String, name: String, email: String, userType: String) {
        val user = hashMapOf(
            "userId" to userId,
            "name" to name,
            "email" to email,
            "userType" to userType,
            "createdAt" to Timestamp.now()
        )

        db.collection("users").document(userId)
            .set(user)
            .addOnSuccessListener {
                Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error saving user data: ${e.message}", Toast.LENGTH_LONG).show()
            }
    }
}