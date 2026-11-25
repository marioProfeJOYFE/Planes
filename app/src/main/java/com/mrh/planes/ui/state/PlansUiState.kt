package com.mrh.planes.ui.state

import com.mrh.planes.data.Plan

data class PlansUiState(
    val plans: List<Plan> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)