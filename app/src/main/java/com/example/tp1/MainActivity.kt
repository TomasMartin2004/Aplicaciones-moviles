package com.example.tp1


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


object AppDestinations {
    const val LOGIN_ROUTE = "login"
    const val REGISTER_ROUTE = "register"
    const val WELCOME_ROUTE = "welcome/{username}"
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppDestinations.LOGIN_ROUTE) {
        composable(route = AppDestinations.LOGIN_ROUTE) {
            LoginScreen(navController = navController)
        }
        composable(route = AppDestinations.REGISTER_ROUTE) {
            RegisterScreen(navController = navController)
        }
        composable(
            route = AppDestinations.WELCOME_ROUTE,
            arguments = listOf(navArgument("username") { type = NavType.StringType })
        ) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: "Usuario"
            WelcomeScreen(navController = navController, username = username)
        }
    }
}
