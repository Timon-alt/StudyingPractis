package com.example.soundproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.soundproject.databinding.ActivityStartBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class StartActivity : AppCompatActivity() {

    lateinit var binding: ActivityStartBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        auth = Firebase.auth

        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignUp.setOnClickListener {
            val email = binding.emailText.text.toString()
            val password = binding.passText.text.toString()

            signIn(email, password)
        }

        binding.textSignUp.setOnClickListener {
            startActivity(Intent(this, RegActivity::class.java))
        }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d("Aboba", "signInWithEmail:success")
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    Log.d("Aboba", "${it.exception}")
                }
            }
    }
}