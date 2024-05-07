package com.example.soundproject

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class Model : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val firebaseAuth = FirebaseAuth.getInstance()


    fun registr(name: String, email: String, password: String,) {
        // Регистрация аккаунта
        auth = Firebase.auth
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    startActivity(Intent(this, StartActivity::class.java))
                } else {
                    Toast.makeText(this, "Овибка", Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun auth() {
        // Вход аккаунта

    }
}