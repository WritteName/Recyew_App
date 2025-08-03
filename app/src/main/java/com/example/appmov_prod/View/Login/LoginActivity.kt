package com.example.appmov_prod.View.Login

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.appmov_prod.HomeActivity
import com.example.appmov_prod.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    private lateinit var btnIngreso: MaterialButton
    private lateinit var btnInGoogle: MaterialButton
    private lateinit var etCorreo: TextInputEditText
    private lateinit var etClave: TextInputEditText
    private lateinit var txtNoClave: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViews()
        setupClickListeners()
    }

    private fun initViews() {
        btnIngreso = findViewById<MaterialButton>(R.id.btnLogin)
        btnInGoogle = findViewById<MaterialButton>(R.id.btnLoginGoogle)
        etCorreo = findViewById<TextInputEditText>(R.id.etCorreo)
        etClave = findViewById<TextInputEditText>(R.id.etClave)
        txtNoClave = findViewById<TextView>(R.id.txtRestablecer)
    }

    private fun setupClickListeners() {
        btnIngreso.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish() // Opcional: para cerrar LoginActivity y que no se pueda volver con "back"
        }
    }
}