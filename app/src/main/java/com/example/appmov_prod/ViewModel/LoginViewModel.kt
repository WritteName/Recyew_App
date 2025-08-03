package com.example.appmov_prod.ViewModel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmov_prod.Repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: AuthRepository = AuthRepository()) : ViewModel() {

    private val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?> = _emailError

    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> = _passwordError

    private val _isFormValid = MutableLiveData<Boolean>(false)
    val isFormValid: LiveData<Boolean> = _isFormValid

    fun onDataChanged(email: String, password: String) {
        val isEmailValid = validateEmail(email)
        val passwordValidation = validatePassword(password)

        _emailError.value = if (!isEmailValid) "Correo inválido" else null
        _passwordError.value = passwordValidation

        _isFormValid.value = isEmailValid && passwordValidation == null
    }

    private fun validateEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun validatePassword(password: String): String? {
        if (password.length < 6) return "Debe tener al menos 6 caracteres"
        if (!password.any { it.isUpperCase() }) return "Debe contener una letra mayúscula"
        if (!password.any { it.isLowerCase() }) return "Debe contener una letra minúscula"
        if (!password.any { it.isDigit() }) return "Debe contener al menos un número"
        if (password.contains(" ")) return "No debe contener espacios"
        return null
    }

    private val _authResult = MutableLiveData<Result<Unit>>()
    val authResult: LiveData<Result<Unit>> = _authResult

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val result = repository.login(email, password)
            _authResult.value = result
        }
    }

    fun register(email: String, password: String) {
        viewModelScope.launch {
            val result = repository.register(email, password)
            _authResult.value = result
        }
    }
}