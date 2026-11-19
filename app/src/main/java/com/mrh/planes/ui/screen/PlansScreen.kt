package com.mrh.planes.ui.screen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mrh.planes.data.Plan
import com.mrh.planes.ui.component.PlanCard
import com.mrh.planes.ui.state.PlansUiState
import com.mrh.planes.ui.theme.PlanesTheme

@Composable
fun PlansScreen(
    uiState: PlansUiState,
    onConfirmPlanClick: (String) -> Unit,
    onRetryClick: () -> Unit
) {
    Scaffold { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Mis planes",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            when {
                uiState.isLoading -> {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            CircularProgressIndicator()

                            Text(text = "Cargando tus planes…")
                        }
                    }
                }

                uiState.errorMessage != null -> {
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor =
                                    MaterialTheme.colorScheme.errorContainer
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(20.dp),
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Text(
                                    text = "No hemos podido mostrar los planes",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )

                                Text(text = uiState.errorMessage)

                                Button(onClick = onRetryClick) {
                                    Text(text = "Reintentar")
                                }
                            }
                        }
                    }
                }

                uiState.plans.isEmpty() -> {
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier.padding(24.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = "Todavía no tienes planes",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )

                                Text(
                                    text = "Aquí aparecerán tus próximas ideas.",
                                    color =
                                        MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }

                else -> {
                    item {
                        val confirmedCount = uiState.plans.count { plan ->
                            plan.isConfirmed
                        }

                        Text(
                            text = "$confirmedCount de " +
                                    "${uiState.plans.size} planes confirmados",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    items(
                        items = uiState.plans,
                        key = { plan -> plan.id }
                    ) { plan ->
                        PlanCard(
                            plan = plan,
                            onConfirmClick = {
                                onConfirmPlanClick(plan.id)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlansScreenPreview() {
    PlanesTheme {
        PlansScreen(
            uiState = PlansUiState(
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
                    )
                )
            ),
            onConfirmPlanClick = {},
            onRetryClick = {}
        )
    }
}