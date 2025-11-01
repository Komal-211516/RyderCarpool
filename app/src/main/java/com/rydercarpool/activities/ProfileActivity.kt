package com.rydercarpool.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rydercarpool.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userId = auth.currentUser?.uid ?: return

        firestore.collection("users").document(userId)
            .get()
            .addOnSuccessListener { document ->
                binding.tvUserName.text = document.getString("name") ?: "Unknown"
                binding.tvUserEmail.text = document.getString("email") ?: "No email"
            }
            .addOnFailureListener {
                binding.tvUserName.text = "Error loading user"
                binding.tvUserEmail.text = ""
            }
    }
}
