package com.example.lex2see.data.auth

import kotlinx.coroutines.delay

/**
 * Temporary repository used only to make the navigation and MVVM flow runnable before Firebase
 * is configured. It intentionally does not persist credentials and must be replaced for release.
 */
class DemoAuthRepository : AuthRepository {
    override suspend fun signInWithPhone(phone: String, password: String): AuthResult {
        delay(450)
        return AuthResult.Success
    }

    override suspend fun signInWithGoogle(): AuthResult {
        delay(150)
        return AuthResult.Error(
            "Google Sign-In chưa được nối Firebase. Hãy thêm google-services.json trước."
        )
    }
}
