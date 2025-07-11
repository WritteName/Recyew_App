package com.example.appmov_prod

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin = findViewById<MaterialButton>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish() // Opcional: para cerrar LoginActivity y que no se pueda volver con "back"
        }
    }
}