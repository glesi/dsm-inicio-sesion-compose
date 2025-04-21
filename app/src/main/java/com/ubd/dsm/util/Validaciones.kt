package com.ubd.dsm.util

fun esCorreoValido(correo: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()
}
