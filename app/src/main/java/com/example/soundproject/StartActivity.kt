package com.example.soundproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.soundproject.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {

    lateinit var binding: ActivityStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textSignUp.setOnClickListener {
            startActivity(Intent(this, RegActivity::class.java))
        }
    }
}