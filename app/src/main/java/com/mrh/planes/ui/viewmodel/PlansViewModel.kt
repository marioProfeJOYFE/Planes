package com.mrh.planes.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.mrh.planes.data.Plan
import com.mrh.planes.ui.state.PlansUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PlansViewModel : ViewModel() {

    // Solo el ViewModel puede modificar este estado.
    private val _uiState = MutableStateFlow(
        PlansUiState(
            plans = listOf(
                Plan(
                    id = "plan-1",
                    name = "Ruta de tapas",
                    city = "Madrid",
                    isConfirmed = false
                ),
                Plan(
                    id = "plan-2",
                    name = "Tarde de museo",
                    city = "Madrid",
                    isConfirmed = true
                ),
                Plan(
                    id = "plan-3",
                    name = "Paseo por el Retiro",
                    city = "Madrid",
                    isConfirmed = false
                )
            )
        )
    )

    // La interfaz puede observarlo, pero no modificarlo.
    val uiState = _uiState.asStateFlow()

    fun onConfirmPlanClick(planId: String) {
        _uiState.update { currentState ->
            currentState.copy(
                plans = currentState.plans.map { plan ->
                    if (plan.id == planId) {
                        plan.copy(
                            isConfirmed = !plan.isConfirmed
                        )
                    } else {
                        plan
                    }
                }
            )
        }
    }

    fun onRetryClick() {
        _uiState.update { currentState ->
            currentState.copy(
                errorMessage = null
            )
        }
    }
}