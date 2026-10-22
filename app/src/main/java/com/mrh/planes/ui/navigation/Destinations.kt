package com.mrh.planes.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

// AQUI VAN TODAS LAS REFERENCIAS A LAS RUTAS DISPONIBLES
// IMPORTANTE: NO ES SU CONTENIDO, SOLO SU DECLARACION


// data object -> Rutas que no necesitan argumentos
@Serializable
data object SitesNav : NavKey

// data class -> Rutas que necesitan argumentos
// LOS ARGUMENTOS QUE QUERAIS
@Serializable
data class SettingsNav(
    val textoEjemplo: String
) : NavKey