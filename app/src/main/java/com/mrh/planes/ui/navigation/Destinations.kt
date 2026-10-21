package com.mrh.planes.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

// AQUI VAN TODAS LAS REFERENCIAS A LAS RUTAS DISPONIBLES
// IMPORTANTE: NO ES SU CONTENIDO, SOLO SU DECLARACION

@Serializable
data object SitesNav : NavKey

@Serializable
data object SettingsNav : NavKey