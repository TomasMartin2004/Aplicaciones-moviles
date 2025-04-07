package com.example.tp1


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tp1.ui.theme.Tp1Theme


object AppDestinations {
    const val LOGIN_ROUTE = "login"
    const val REGISTER_ROUTE = "register"
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tp1Theme {
                AppNavigation() //llama al Composable de navegacion (implementado abajo)
            }
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

    }
}
