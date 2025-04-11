package com.example.tp1

import android.content.Context

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

import com.example.tp1.ui.theme.Nord0
import com.example.tp1.ui.theme.Nord1
import com.example.tp1.ui.theme.Nord2
import com.example.tp1.ui.theme.Nord3
import com.example.tp1.ui.theme.White

import androidx.navigation.NavHostController
import androidx.compose.ui.Alignment

@Composable
fun WelcomeScreen(navController: NavHostController, username: String) {
    val context = LocalContext.current
    var selectedPlatform by remember { mutableStateOf("") }
    val preferences = listOf("Programación", "Redes", "Seguridad", "Hardware", "Otra")
    val selectedPrefs = remember { mutableStateMapOf<String, Boolean>() }
    preferences.forEach { pref -> selectedPrefs.putIfAbsent(pref, false) }

    var otherPreference by remember { mutableStateOf("") }




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
                text = "Bienvenido, $username.",
                style = MaterialTheme.typography.headlineMedium,
                color = White
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Selector de plataforma
            Text(text = "Selecciona tu plataforma:", color = White )
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("Android", "iOS").forEach { platform ->
                    Button(
                        onClick = { selectedPlatform = platform },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedPlatform == platform) White else Nord2,
                            contentColor =  if (selectedPlatform == platform) Nord2 else White
                        )
                    ) {
                        Text(platform)
                    }
                }

                //TODO Icono Android e IOs
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Selecciona tus preferencias:", color = White)
            preferences.forEach { pref ->
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Checkbox(
                            checked = selectedPrefs[pref] == true,
                            onCheckedChange = { checked -> selectedPrefs[pref] = checked }
                        )
                        Spacer(modifier = Modifier.width(8.dp)) // Espacio entre checkbox y texto
                        Text(text = pref, color = White )
                    }

                    // Campo adicional si el usuario eligió "Otra"
                    if (pref == "Otra" && selectedPrefs["Otra"] == true) {
                        OutlinedTextField(
                            value = otherPreference,
                            onValueChange = { otherPreference = it },
                            label = { Text("Especifica tu preferencia", color = Nord2 ) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 32.dp), // Alineación con los textos
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = White,
                                unfocusedTextColor = White,
                                focusedBorderColor = Nord2,
                                unfocusedBorderColor = Nord3,
                                cursorColor = White,
                                errorLabelColor = MaterialTheme.colorScheme.error
                            )
                        )
                    }
                }
            }


        }



    }


}

