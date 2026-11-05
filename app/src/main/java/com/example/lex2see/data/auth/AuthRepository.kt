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
    suspend fun register(
        email: String,
        password: String,
        displayName: String
    ): Result<Unit>

    suspend fun login(
        email: String,
        password: String
    ): Result<Unit>

    suspend fun ensureUserProfile(): Result<Unit>

    fun logout()
}
