package com.mrh.planes.ui.state

import com.mrh.planes.data.Site

data class SiteDetailUiState (
    val sitio: Site? = null,
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)