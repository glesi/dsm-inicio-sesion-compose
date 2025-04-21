package com.ubd.dsm.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ubd.dsm.navigation.Rutas
import com.ubd.dsm.util.esCorreoValido

@Composable
fun PantallaInicioSesion(navController: NavController) {
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var mostrarContrasena by remember { mutableStateOf(false) }
    var mensajeError by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Iniciar Sesión", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (mostrarContrasena) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icono = if (mostrarContrasena) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                IconButton(onClick = { mostrarContrasena = !mostrarContrasena }) {
                    Icon(imageVector = icono, contentDescription = "Ver contraseña")
                }
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (mensajeError.isNotEmpty()) {
            Text(text = mensajeError, color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = {
                mensajeError = when {
                    correo.isBlank() || contrasena.isBlank() -> "Todos los campos son obligatorios."
                    !esCorreoValido(correo) -> "Correo inválido."
                    else -> {
                        navController.navigate(Rutas.Bienvenida.crearRuta(correo.substringBefore("@")))
                        ""
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Entrar")
        }
    }
}