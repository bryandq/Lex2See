package com.example.lex2see.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.lex2see.data.auth.*
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: AuthRepository): ViewModel() {
    var busy by mutableStateOf(false); private set
    var error by mutableStateOf<String?>(null); private set
    var completed by mutableStateOf(false); private set
    fun register(email: String, password: String, confirm: String, name: String) {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) { error = "Email không hợp lệ"; return }
        if (password.length < 8) { error = "Mật khẩu cần ít nhất 8 ký tự"; return }
        if (password != confirm) { error = "Mật khẩu xác nhận không khớp"; return }
        viewModelScope.launch {
            busy = true; error = null
            when(val result = repository.register(email, password, name)) {
                AuthResult.Success -> completed = true
                is AuthResult.Error -> error = result.message
            }
            busy = false
        }
    }
}

@Composable
fun RegisterScreen(repository: AuthRepository, onBack: () -> Unit, onSuccess: () -> Unit) {
    val vm: RegisterViewModel = viewModel(factory = object: androidx.lifecycle.ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T: ViewModel> create(modelClass: Class<T>): T = RegisterViewModel(repository) as T
    })
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf("") }
    LaunchedEffect(vm.completed) { if(vm.completed) onSuccess() }
    Column(Modifier.fillMaxSize().padding(28.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        TextButton(onClick = onBack) { Text("← Quay lại") }
        Text("Tạo tài khoản thui!", style = MaterialTheme.typography.headlineMedium)
        Text("Chào mừng đến với Lex2See")
        OutlinedTextField(name, { name = it }, label = { Text("Tên hiển thị") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(email, { email = it }, label = { Text("Email") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email), modifier = Modifier.fillMaxWidth())
        OutlinedTextField(password, { password = it }, label = { Text("Mật khẩu") }, visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth())
        OutlinedTextField(confirm, { confirm = it }, label = { Text("Xác nhận mật khẩu") }, visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth())
        vm.error?.let { Text(it, color = MaterialTheme.colorScheme.error) }
        Button(onClick = { vm.register(email.trim(),password,confirm,name.trim()) }, enabled = !vm.busy, modifier = Modifier.fillMaxWidth()) { Text(if(vm.busy) "Đang tạo..." else "Đăng ký") }
    }
}
