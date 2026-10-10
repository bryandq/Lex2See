package com.example.lex2see.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.lex2see.data.auth.AuthRepository
import com.example.lex2see.data.auth.AuthResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class LoginUiState(
    val phone: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val loginSucceeded: Boolean = false,
    val message: String? = null
)

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onPhoneChanged(value: String) {
        val normalized = value.filter(Char::isDigit).take(10)
        _uiState.update { it.copy(phone = normalized, message = null) }
    }

    fun onPasswordChanged(value: String) {
        _uiState.update { it.copy(password = value, message = null) }
    }

    fun login() {
        val state = _uiState.value
        val validationMessage = validate(state.phone, state.password)
        if (validationMessage != null) {
            _uiState.update { it.copy(message = validationMessage) }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, message = null) }
            when (val result = authRepository.signInWithPhone(state.phone, state.password)) {
                AuthResult.Success -> _uiState.update {
                    it.copy(isLoading = false, loginSucceeded = true)
                }
                is AuthResult.Error -> _uiState.update {
                    it.copy(isLoading = false, message = result.message)
                }
            }
        }
    }

    fun loginWithGoogle() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, message = null) }
            when (val result = authRepository.signInWithGoogle()) {
                AuthResult.Success -> _uiState.update {
                    it.copy(isLoading = false, loginSucceeded = true)
                }
                is AuthResult.Error -> _uiState.update {
                    it.copy(isLoading = false, message = result.message)
                }
            }
        }
    }

    fun onLoginHandled() {
        _uiState.update { it.copy(loginSucceeded = false) }
    }

    fun onMessageShown() {
        _uiState.update { it.copy(message = null) }
    }

    private fun validate(phone: String, password: String): String? {
        if (phone.length != 10 || !phone.startsWith("0")) {
            return "Số điện thoại phải gồm 10 chữ số và bắt đầu bằng 0."
        }
        if (password.length < 8) {
            return "Mật khẩu phải có ít nhất 8 ký tự."
        }
        return null
    }
}

class LoginViewModelFactory(
    private val authRepository: AuthRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(authRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
