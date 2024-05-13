package com.example.soundproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import com.example.soundproject.databinding.ActivityRegBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class RegActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegBinding
    private val presenter: Presenter = Presenter()
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        database = Firebase.database.reference

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
            val nickname = binding.nickInput.text.toString()

            binding.progressBar.visibility = View.VISIBLE

           // presenter.registr(name, email, password, rePassword)
            createAccount(email, password, nickname)
        }
    }

    private fun createAccount(email: String, password:String, username: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d("Aboba", "createUserWithEmailAndPassword: success")

                    writeNewUser(username, email)

                    startActivity(Intent(this, StartActivity::class.java))

                    binding.progressBar.visibility = View.GONE
                } else {
                    Log.d("Aboba", "${it.exception}")
                }
            }
    }

    private fun writeNewUser(username: String, email: String) {
        val user = User(username, email)

        database.child("users").child(username).setValue(user)
    }
}