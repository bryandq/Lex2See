package com.example.lex2see.data.auth

sealed interface AuthResult {
    data object Success : AuthResult
    data class Error(val message: String) : AuthResult
}

/**
 * Abstraction for authentication so the UI/ViewModel does not depend directly on Firebase.
 * Replace DemoAuthRepository with FirebaseAuthRepository after google-services.json is added.
 */
interface AuthRepository {
    suspend fun signInWithPhone(phone: String, password: String): AuthResult
    suspend fun signInWithGoogle(): AuthResult
}
