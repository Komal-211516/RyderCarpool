package com.rydercarpool

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val nameInput = findViewById<EditText>(R.id.editTextName)
        val emailInput = findViewById<EditText>(R.id.editTextEmail)
        val passwordInput = findViewById<EditText>(R.id.editTextPassword)
        val userTypeGroup = findViewById<RadioGroup>(R.id.radioGroupUserType)
        val signupButton = findViewById<Button>(R.id.buttonSignup)

        signupButton.setOnClickListener {
            val name = nameInput.text.toString()
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()
            val selectedTypeId = userTypeGroup.checkedRadioButtonId

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || selectedTypeId == -1) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val userType = findViewById<RadioButton>(selectedTypeId).text.toString()

            Toast.makeText(this, "Signed up as $userType", Toast.LENGTH_SHORT).show()
        }
    }
}
