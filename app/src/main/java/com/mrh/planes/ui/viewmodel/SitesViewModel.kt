package com.mrh.planes.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.mrh.planes.data.listaSitios
import com.mrh.planes.ui.state.SitesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SitesViewModel : ViewModel() {

    //SOLO SE PUEDE TOCAR DESDE el VIEWMODEL,
    // y es el que sufrirá las modificaciones
    private val _uiState = MutableStateFlow(
        SitesUiState(sitios = listaSitios)
    )

    // SOLO SE ENCARGA DE MOSTRAR EL ESTADO ACTUAL de _uiState en la INTERFAZ
    // que consuma este ViewModel
    val uiState = _uiState.asStateFlow()

    //Bloque de codigo que se ejecuta en cuanto se crea este ViewModel
    init {

    }

    fun cambiarFavorito(sitioId: Int){
        _uiState.update { estadoActual ->
            estadoActual.copy(
                sitios = estadoActual.sitios.map { sitio ->
                    if(sitio.id == sitioId){
                        sitio.copy(
                            esFavorito = !sitio.esFavorito
                        )
                    }else{
                        sitio
                    }
                }
            )
        }
    }


}