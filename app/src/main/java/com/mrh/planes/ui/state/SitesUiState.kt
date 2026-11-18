package com.mrh.planes.ui.state

import com.mrh.planes.data.Site

/**
 * Clase que DEBE consumir nuestro ViewModel para luego servir en la interfaz
 */
data class SitesUiState(
    val sitios: List<Site> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
