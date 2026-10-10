package com.example.lex2see.app

import android.content.Context
import com.example.lex2see.data.auth.AuthRepository
import com.example.lex2see.data.auth.DemoAuthRepository
import com.example.lex2see.data.local.DatabaseProvider
import com.example.lex2see.data.local.Lex2SeeDatabase

class AppContainer(context: Context) {
    val database: Lex2SeeDatabase = DatabaseProvider.get(context.applicationContext)

    // TODO Firebase milestone: replace with FirebaseAuthRepository.
    val authRepository: AuthRepository = DemoAuthRepository()
}
