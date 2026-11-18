package com.mrh.planes.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

// Direccion a la que navegar que NO recibe ningun argumento
@Serializable
data object SitesNav : NavKey

// Direccion a la que navegar que SI recibe argumentos
@Serializable
data class SiteDetailNav( val siteId : Int) : NavKey