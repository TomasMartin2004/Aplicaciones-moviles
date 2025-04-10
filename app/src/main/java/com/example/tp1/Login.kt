package com.example.tp1

import android.content.Context
import android.net.Uri

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

import com.example.tp1.ui.theme.Nord0
import com.example.tp1.ui.theme.Nord2
import com.example.tp1.ui.theme.Nord3
import com.example.tp1.ui.theme.White

import androidx.navigation.NavHostController
import androidx.compose.ui.Alignment


//pasa a ser un composable en vez de un activity
@Composable
fun LoginScreen(navController: NavHostController) {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var usernameError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    val correctUsername = "Juan Torres"
    val correctPassword = "1234utn"

    fun validate(): Boolean {
        usernameError = if (username.isBlank()) "El usuario no puede estar vacío" else null
        passwordError = if (password.isBlank()) "La contraseña no puede estar vacía" else null

        return usernameError == null && passwordError == null
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Nord0
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Iniciar Sesión",
                style = MaterialTheme.typography.headlineMedium,
                color = White
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = username,
                onValueChange = { username = it; usernameError = null },
                label = { Text("Usuario", color = Nord2) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = usernameError != null,
                supportingText = { if (usernameError != null) Text(usernameError!!, color = MaterialTheme.colorScheme.error) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = White,
                    unfocusedTextColor = White,
                    focusedBorderColor = Nord3,
                    unfocusedBorderColor = Nord3,
                    cursorColor = White,
                    errorLabelColor = MaterialTheme.colorScheme.error

                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it; passwordError = null },
                label = { Text("Contraseña", color = Nord2) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = passwordError != null,
                supportingText = { if (passwordError != null) Text(passwordError!!, color = MaterialTheme.colorScheme.error) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = White,
                    unfocusedTextColor = White,
                    focusedBorderColor = Nord2,
                    unfocusedBorderColor = Nord3,
                    cursorColor = White,
                    errorLabelColor = MaterialTheme.colorScheme.error
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (validate()) {
                        if (username == correctUsername && password == correctPassword) {
                            Toast.makeText(context, "Login exitoso", Toast.LENGTH_SHORT).show()
                            navController.navigate("welcome/${Uri.encode(username)}")
                        } else {
                            Toast.makeText(context, "Datos incorrectos", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Nord2,
                    contentColor = White
                )
            ) {
                Text("Ingresar")
            }

            TextButton(
                onClick = {
                    navController.navigate(AppDestinations.REGISTER_ROUTE) //navega a la ruta de registro
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    "¿No tienes cuenta? Regístrate aquí",
                    color = Nord3 // Usa colores de tu tema
                )
            }

        }
    }
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
