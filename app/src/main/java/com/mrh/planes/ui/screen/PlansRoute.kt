package com.mrh.planes.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mrh.planes.ui.viewmodel.PlansViewModel

@Composable
fun PlansRoute(
    viewModel: PlansViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PlansScreen(
        uiState = uiState,
        onConfirmPlanClick = { planId ->
            viewModel.onConfirmPlanClick(planId)
        },
        onRetryClick = {
            viewModel.onRetryClick()
        }
    )
}