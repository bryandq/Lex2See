package com.example.lex2see.navigation

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lex2see.data.auth.AuthRepository
import com.example.lex2see.ui.auth.ForgotPasswordScreen
import com.example.lex2see.ui.auth.LoginRoute
import com.example.lex2see.ui.auth.LoginViewModel
import com.example.lex2see.ui.auth.LoginViewModelFactory
import com.example.lex2see.ui.home.HomeScreen

@Composable
fun Lex2SeeNavHost(
    authRepository: AuthRepository
) {
    val navController = rememberNavController()
    val activity = LocalContext.current as? ComponentActivity

    NavHost(
        navController = navController,
        startDestination = AppDestination.Login.route
    ) {
        composable(AppDestination.Login.route) {
            val loginViewModel: LoginViewModel = viewModel(
                factory = LoginViewModelFactory(authRepository)
            )

            LoginRoute(
                viewModel = loginViewModel,
                onBack = { activity?.finish() },
                onForgotPassword = {
                    navController.navigate(AppDestination.ForgotPassword.route)
                },
                onLoginSuccess = {
                    navController.navigate(AppDestination.Home.route) {
                        popUpTo(AppDestination.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(AppDestination.ForgotPassword.route) {
            ForgotPasswordScreen(onBack = { navController.popBackStack() })
        }

        composable(AppDestination.Home.route) {
            HomeScreen(
                onSignOut = {
                    navController.navigate(AppDestination.Login.route) {
                        popUpTo(AppDestination.Home.route) { inclusive = true }
                    }
                }
            )
        }
    }
}
