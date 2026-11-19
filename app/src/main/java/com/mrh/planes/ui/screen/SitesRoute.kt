package com.mrh.planes.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mrh.planes.ui.viewmodel.SitesViewModel

@Composable
fun SitesRoute(
    viewModel: SitesViewModel,
    modifier: Modifier = Modifier,
    onDetailNavigation: (Int) -> Unit
){
    // Guardamos el objeto XXXXUiState (ej. SitesUiState)  para observar sus cambios del ViewModel
    // sin necesidad de actualizarlo de forma manual
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SitesScreen(
        modifier = modifier,
        onDetailNavigation = onDetailNavigation,
        uiState = uiState,
        onFavoriteClick = { sitioId ->
            viewModel.cambiarFavorito(sitioId = sitioId)
        },
        onBusquedaChanged = { textoBusqueda ->
            viewModel.cambiarBusqueda(textoBusqueda)
        }
    )


}