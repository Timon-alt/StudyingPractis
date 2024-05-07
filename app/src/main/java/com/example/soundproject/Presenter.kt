package com.example.soundproject

import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Presenter : AppCompatActivity() {

    private val model : Model = Model()

    fun registr(name: String, email: String, password: String, rePassword: String) {
        // Проверяет валидность мыла и пароля при регистрации
        if (email == "" && password == "" && rePassword == "") {
            Toast.makeText(this, "Пустые поля", Toast.LENGTH_SHORT).show()
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Мыло неправильное", Toast.LENGTH_SHORT).show()
        }

        if (password != rePassword) {
            Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
        }

        if (Patterns.EMAIL_ADDRESS.matcher(email).matches() && password == rePassword) {
            model.registr(name, email, password)
        }

    }
}