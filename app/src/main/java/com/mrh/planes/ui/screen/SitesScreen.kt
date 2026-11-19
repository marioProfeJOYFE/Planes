package com.mrh.planes.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mrh.planes.data.listaSitios
import com.mrh.planes.ui.component.SiteCard
import com.mrh.planes.ui.state.SitesUiState
import com.mrh.planes.ui.theme.PlanesTheme

@Composable
fun SitesScreen(
    uiState: SitesUiState,
    modifier: Modifier = Modifier,
    onFavoriteClick: (Int) -> Unit,
    onDetailNavigation: (Int) -> Unit
) {
    // En clase trasladaremos la búsqueda al ViewModel.
    var textoBusqueda by remember { mutableStateOf("") }
    var soloFavoritos by remember { mutableStateOf(false) }

    val teclado = LocalSoftwareKeyboardController.current

    // Calculamos los resultados a partir de los datos actuales.
    val sitiosFiltrados = uiState.sitios.filter { sitio ->
        val coincideBusqueda =
            sitio.nombre.contains(textoBusqueda, ignoreCase = true) ||
                    sitio.direccion.contains(textoBusqueda, ignoreCase = true)

        val coincideFavorito = !soloFavoritos || sitio.esFavorito

        coincideBusqueda && coincideFavorito
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            horizontal = 20.dp,
            vertical = 24.dp
        ),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Cabecera
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "Mis sitios",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Buscador
        item {
            OutlinedTextField(
                value = textoBusqueda,
                onValueChange = { texto ->
                    textoBusqueda = texto
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "Buscar por nombre o dirección")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = null
                    )
                },
                trailingIcon = {
                    if (textoBusqueda.isNotEmpty()) {
                        IconButton(
                            onClick = {
                                textoBusqueda = ""
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Close,
                                contentDescription = "Limpiar búsqueda"
                            )
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        teclado?.hide()
                    }
                )
            )
        }

        // Filtros
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = !soloFavoritos,
                    onClick = {
                        soloFavoritos = false
                    },
                    label = {
                        Text(text = "Todos")
                    }
                )

                FilterChip(
                    selected = soloFavoritos,
                    onClick = {
                        soloFavoritos = true
                    },
                    label = {
                        Text(text = "Favoritos")
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Star,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                )
            }
        }

        when {
            uiState.isLoading -> {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            uiState.errorMessage != null -> {
                item {
                    Text(
                        text = uiState.errorMessage,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
            }

            sitiosFiltrados.isEmpty() -> {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor =
                                MaterialTheme.colorScheme.surfaceContainer
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(24.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "No hay sitios que mostrar",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = if (textoBusqueda.isNotBlank()) {
                                    "Prueba con otro nombre o dirección."
                                } else if (soloFavoritos) {
                                    "Pulsa la estrella de un sitio para guardarlo."
                                } else {
                                    "Aquí aparecerán tus sitios."
                                },
                                color =
                                    MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            else -> {
                item {
                    Text(
                        text = if (sitiosFiltrados.size == 1) {
                            "1 sitio"
                        } else {
                            "${sitiosFiltrados.size} sitios"
                        },
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                items(
                    items = sitiosFiltrados,
                    key = { sitio -> sitio.id }
                ) { sitio ->
                    SiteCard(
                        sitio = sitio,
                        onFavoritoClick = {
                            onFavoriteClick(sitio.id)
                        },
                        onClick = { sitioId ->
                            onDetailNavigation(sitioId)
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SitesScreenPreview() {
    PlanesTheme {
        SitesScreen(
            uiState = SitesUiState(sitios = listaSitios),
            onFavoriteClick = {},
            onDetailNavigation = {}
        )
    }
}