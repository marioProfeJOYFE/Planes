package com.mrh.planes.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.mrh.planes.data.listaSitios
import com.mrh.planes.ui.state.SiteDetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SiteDetailViewModel(private val siteId: Int) : ViewModel() {

    private val _uiState = MutableStateFlow(SiteDetailUiState())

    val uiState = _uiState.asStateFlow()

    init {
        cargarSitio()
    }

    private fun cargarSitio(){
        val sitioEncontrado = listaSitios.find { sitio ->
            sitio.id == siteId
        }

        _uiState.update { currentState ->
            currentState.copy(
                sitio = sitioEncontrado,
                errorMessage = if(sitioEncontrado == null) "No se ha encontrado" else null
            )
        }

    }

}