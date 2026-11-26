package com.mrh.planes.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mrh.planes.ui.viewmodel.SiteDetailViewModel

@Composable
fun SiteDetailRoute(
    viewModel: SiteDetailViewModel,
    volver: () -> Unit,
    modifier: Modifier = Modifier
){

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SiteDetailScreen(
        volver = volver,
        uiState = uiState
    )
}