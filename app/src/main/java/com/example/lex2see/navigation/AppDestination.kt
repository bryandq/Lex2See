package com.example.lex2see.navigation

sealed class AppDestination(val route: String) {
    data object Login : AppDestination("login")
    data object ForgotPassword : AppDestination("forgot_password")
    data object Home : AppDestination("home")
}
