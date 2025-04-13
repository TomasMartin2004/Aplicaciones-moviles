package com.example.tp1

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.tp1.ui.theme.Nord0
import com.example.tp1.ui.theme.Nord1
import com.example.tp1.ui.theme.Nord2
import com.example.tp1.ui.theme.Nord3
import com.example.tp1.ui.theme.Nord4
import com.example.tp1.ui.theme.White

@Composable
fun RegisterScreen(navController: NavHostController) {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var nameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var confirmPasswordError by remember { mutableStateOf<String?>(null) }

    fun validate(): Boolean {
        nameError = if (username.isBlank()) "El nombre no puede estar vacío" else null
        emailError = if (email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches()
        ) "Ingrese un e-mail válido" else null
        passwordError =
            if (password.length < 6) "La contraseña debe tener al menos 6 caracteres" else null
        confirmPasswordError =
            if (password != confirmPassword) "Las contraseñas no coinciden" else null

        return nameError == null && emailError == null && passwordError == null && confirmPasswordError == null
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Nord0
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Surface(
                modifier = Modifier
                    .widthIn(max = 400.dp)
                    .heightIn(min = 300.dp, max = 700.dp)
                    .padding(32.dp),
                color = Nord1,
                shape = MaterialTheme.shapes.medium,
                tonalElevation = 4.dp,
                shadowElevation = 8.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Registro de Usuario",
                        style = MaterialTheme.typography.headlineMedium,
                        color = White
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    OutlinedTextField(
                        value = username,
                        onValueChange = { username = it; nameError = null },
                        label = { Text("Nombre Completo", color = Nord3) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        isError = nameError != null,
                        supportingText = {
                            if (nameError != null) Text(
                                nameError!!,
                                color = MaterialTheme.colorScheme.error
                            )
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = White,
                            unfocusedTextColor = White,
                            focusedBorderColor = Nord3,
                            unfocusedBorderColor = Nord2,
                            cursorColor = White,
                            focusedLabelColor = White,
                            unfocusedLabelColor = Nord2,
                            errorBorderColor = MaterialTheme.colorScheme.error,
                            errorLabelColor = MaterialTheme.colorScheme.error
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it; emailError = null },
                        label = { Text("E-mail", color = Nord3) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        isError = emailError != null,
                        supportingText = {
                            if (emailError != null) Text(
                                emailError!!,
                                color = MaterialTheme.colorScheme.error
                            )
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = White,
                            unfocusedTextColor = White,
                            focusedBorderColor = Nord3,
                            unfocusedBorderColor = Nord2,
                            cursorColor = White,
                            focusedLabelColor = White,
                            unfocusedLabelColor = Nord2,
                            errorBorderColor = MaterialTheme.colorScheme.error,
                            errorLabelColor = MaterialTheme.colorScheme.error
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it; passwordError = null; confirmPasswordError = null
                        },
                        label = { Text("Contraseña", color = Nord3) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        isError = passwordError != null,
                        supportingText = {
                            if (passwordError != null) Text(
                                passwordError!!,
                                color = MaterialTheme.colorScheme.error
                            )
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = White,
                            unfocusedTextColor = White,
                            focusedBorderColor = Nord3,
                            unfocusedBorderColor = Nord2,
                            cursorColor = White,
                            focusedLabelColor = White,
                            unfocusedLabelColor = Nord2,
                            errorBorderColor = MaterialTheme.colorScheme.error,
                            errorLabelColor = MaterialTheme.colorScheme.error
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it; confirmPasswordError = null },
                        label = { Text("Repetir Contraseña", color = Nord3) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        isError = confirmPasswordError != null,
                        supportingText = {
                            if (confirmPasswordError != null) Text(
                                confirmPasswordError!!,
                                color = MaterialTheme.colorScheme.error
                            )
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = White,
                            unfocusedTextColor = White,
                            focusedBorderColor = Nord3,
                            unfocusedBorderColor = Nord2,
                            cursorColor = White,
                            focusedLabelColor = White,
                            unfocusedLabelColor = Nord2,
                            errorBorderColor = MaterialTheme.colorScheme.error,
                            errorLabelColor = MaterialTheme.colorScheme.error
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            if (validate()) {
                                showToast(context, "Registro exitoso")
                                navController.popBackStack()
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Nord2,
                            contentColor = White
                        )
                    ) {
                        Text("Registrarse")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    TextButton(
                        onClick = {
                            navController.popBackStack()
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            "¿Ya tenés cuenta? Inicia Sesión",
                            color = Nord4
                        )
                    }
                }
            }
        }
    }
}