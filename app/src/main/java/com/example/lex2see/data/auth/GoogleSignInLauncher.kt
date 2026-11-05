package com.example.lex2see.data.auth

import android.content.Context

import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest

import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential

class GoogleSignInLauncher(
    private val context: Context
) {

    suspend fun getIdToken(
        webClientId: String
    ): String {

        val credentialManager =
            CredentialManager.create(context)

        val googleIdOption =
            GetGoogleIdOption.Builder()
                .setServerClientId(webClientId)
                .setFilterByAuthorizedAccounts(false)
                .build()

        val request =
            GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()

        val result =
            credentialManager.getCredential(
                context = context,
                request = request
            )

        val credential = result.credential

        if (
            credential is CustomCredential &&
            credential.type ==
            GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
        ) {

            val googleCredential =
                GoogleIdTokenCredential.createFrom(
                    credential.data
                )

            return googleCredential.idToken
        }

        throw IllegalStateException(
            "Không nhận được Google ID token"
        )
    }
}