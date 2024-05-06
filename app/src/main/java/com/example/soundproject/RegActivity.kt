package com.example.soundproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.soundproject.databinding.ActivityRegBinding

class RegActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}