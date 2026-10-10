package com.example.lex2see

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.lex2see.app.AppContainer
import com.example.lex2see.navigation.Lex2SeeNavHost
import com.example.lex2see.ui.theme.Lex2seeTheme

class MainActivity : ComponentActivity() {

    private lateinit var appContainer: AppContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        appContainer = AppContainer(applicationContext)

        setContent {
            Lex2seeTheme {
                Lex2SeeNavHost(authRepository = appContainer.authRepository)
            }
        }
    }
}