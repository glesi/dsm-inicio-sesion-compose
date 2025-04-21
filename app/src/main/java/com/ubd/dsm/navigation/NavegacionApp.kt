package com.ubd.dsm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ubd.dsm.ui.screen.PantallaBienvenida
import com.ubd.dsm.ui.screen.PantallaInicioSesion

sealed class Rutas(val ruta: String) {
    object InicioSesion : Rutas("inicio_sesion")
    object Bienvenida : Rutas("bienvenida/{nombre}") {
        fun crearRuta(nombre: String) = "bienvenida/$nombre"
    }
}

@Composable
fun NavegacionApp(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Rutas.InicioSesion.ruta) {
        composable(Rutas.InicioSesion.ruta) {
            PantallaInicioSesion(navController)
        }
        composable(Rutas.Bienvenida.ruta) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre") ?: "Usuario"
            PantallaBienvenida(nombre)
        }
    }
}
