package com.example.appmov_prod.View.Login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.appmov_prod.HomeActivity
import com.example.appmov_prod.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.example.appmov_prod.ViewModel.LoginViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var googleSignInClient: GoogleSignInClient

    private lateinit var btnIngreso: MaterialButton
    private lateinit var inputLayoutEmail: TextInputLayout
    private lateinit var etCorreo: TextInputEditText
    private lateinit var inputLayoutPassword: TextInputLayout
    private lateinit var etClave: TextInputEditText
    private lateinit var inputConfirmPassword: TextInputLayout
    private lateinit var etRepClave: TextInputEditText
    private lateinit var txtNoClave: TextView
    private lateinit var btnGoogle: SignInButton
    private lateinit var txtMessage: TextView
    private lateinit var txtMode: TextView
    private lateinit var txtTitulo: TextView

    private var isLoginMode = true
    private val GOOGLE_SIGN_IN_CODE = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViews()
        setupClickListeners()
        observeViewModel()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun initViews() {
        txtTitulo = findViewById(R.id.tvTitulo)
        inputLayoutEmail = findViewById(R.id.inputEmail)
        etCorreo = findViewById(R.id.etCorreo)
        inputLayoutPassword = findViewById(R.id.inputPassword)
        etClave = findViewById(R.id.etClave)
        inputConfirmPassword = findViewById(R.id.inputConfirmPassword)
        etRepClave = findViewById(R.id.etReClave)
        txtNoClave = findViewById(R.id.txtRestablecer)
        btnIngreso = findViewById(R.id.btnLogin)
        btnGoogle = findViewById(R.id.btnLoginGoogle)
        txtMessage = findViewById(R.id.txtOp)
        txtMode = findViewById(R.id.txtCion)
    }

    private fun setupClickListeners() {
        btnIngreso.setOnClickListener {
            val email = etCorreo.text.toString().trim()
            val password = etClave.text.toString().trim()

            if (isLoginMode) {
                viewModel.login(email, password)
            } else {
                viewModel.register(email, password)
            }
        }
        txtMode.setOnClickListener {
            toggleMode()
        }

        btnGoogle.setOnClickListener {
            signInWithGoogle()
        }

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!isLoginMode) {
                    viewModel.onDataChanged(
                        etCorreo.text.toString(),
                        etClave.text.toString()
                    )
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
        etCorreo.addTextChangedListener(textWatcher)
        etClave.addTextChangedListener(textWatcher)
    }

    private fun observeViewModel() {
        viewModel.emailError.observe(this) { error ->
            inputLayoutEmail.error = error
        }
        viewModel.passwordError.observe(this) { error ->
            inputLayoutPassword.error = error
        }
        viewModel.isFormValid.observe(this) { isValid ->
            if (!isLoginMode) {
                btnIngreso.isEnabled = isValid
            } else {
                btnIngreso.isEnabled = true
            }
        }

        viewModel.authResult.observe(this) { result ->
            result.onSuccess {
                if(isLoginMode){
                    showWelcome()
                    NavigateHome()
                }else{
                    ShowAlert("Registro exitoso.")
                    toggleMode()
                }

            }
            result.onFailure {
                ShowAlert(it.message ?: "Error al autenticar.")
            }
        }
    }

    private fun toggleMode() {
        isLoginMode = !isLoginMode

        if (isLoginMode) {
            txtTitulo.text = "Login"
            txtNoClave.visibility = View.VISIBLE
            btnIngreso.text = "Login"
            txtMessage.text = "Don't Have an account?"
            txtMode.text = "Register Now"
        } else {
            txtTitulo.text = "SignUp"
            txtNoClave.visibility = View.GONE
            btnIngreso.text = "Register"
            txtMessage.text = "Already have an account?"
            txtMode.text = "Login"
        }

        inputLayoutEmail.error = null
        etCorreo.text?.clear()
        inputLayoutPassword.error = null
        etClave.text?.clear()
        etRepClave.text?.clear()
    }

    private fun ShowAlert(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun showWelcome() {
        Toast.makeText(this, "Bienvenido a Recyew", Toast.LENGTH_LONG).show()
    }

    private fun NavigateHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    private fun signInWithGoogle(){
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GOOGLE_SIGN_IN_CODE) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                val idToken = account.idToken
                if (idToken != null) {
                    viewModel.signInWithGoogle(idToken)
                } else {
                    ShowAlert("No se pudo obtener el ID Token")
                }
            } catch (e: ApiException) {
                ShowAlert("Google Sign-In falló: ${e.localizedMessage}")
            }
        }
    }
}