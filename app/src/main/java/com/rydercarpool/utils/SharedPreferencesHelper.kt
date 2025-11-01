package com.rydercarpool.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesHelper(context: Context) {

    private val sharedPreferences: SharedPreferences = 
        context.getSharedPreferences("RyderCarpoolPrefs", Context.MODE_PRIVATE)

    // Login state
    fun saveLoginState(isLoggedIn: Boolean) {
        sharedPreferences.edit().putBoolean("isLoggedIn", isLoggedIn).apply()
    }

    fun getLoginState(): Boolean {
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }

    // User email
    fun saveUserEmail(email: String) {
        sharedPreferences.edit().putString("userEmail", email).apply()
    }

    fun getUserEmail(): String {
        return sharedPreferences.getString("userEmail", "") ?: ""
    }

    // User name
    fun saveUserName(name: String) {
        sharedPreferences.edit().putString("userName", name).apply()
    }

    fun getUserName(): String {
        return sharedPreferences.getString("userName", "") ?: ""
    }

    // Clear all data
    fun clearUserData() {
        sharedPreferences.edit().clear().apply()
    }
}