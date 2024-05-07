package com.example.soundproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.soundproject.databinding.ActivityRegBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class RegActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegBinding
    private val presenter: Presenter = Presenter()
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            //reload()
        }

        binding = ActivityRegBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnReg.setOnClickListener {

            val email = binding.emInput.text.toString()
            val password = binding.passInput.text.toString()


           // presenter.registr(name, email, password, rePassword)
            createAccount(email, password)
        }
    }

    private fun createAccount(email: String, password:String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d("Aboba", "createUserWithEmailAndPassword: success")
                } else {
                    Log.d("Aboba", "${it.exception}")
                }
            }

    }
}