package com.mrh.planes.ui.navigation

import android.util.Log
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.EventNote
import androidx.compose.material.icons.outlined.EventNote
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.mrh.planes.ui.screen.PlansRoute
import com.mrh.planes.ui.screen.SiteDetailScreen
import com.mrh.planes.ui.screen.SitesRoute
import com.mrh.planes.ui.viewmodel.PlansViewModel
import com.mrh.planes.ui.viewmodel.SitesViewModel


@Composable
fun Navigator() {
    val backStack = rememberNavBackStack(SitesNav)

    val volver = {
        if (backStack.size > 1) {
            backStack.removeLastOrNull()
        }
    }

    val pantallaActual = backStack.lastOrNull()

    val mostrarNavigationBar = pantallaActual is SitesNav || pantallaActual is PlansNav

    val sitesViewModel: SitesViewModel = viewModel()

    val plansViewModel: PlansViewModel = viewModel()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        // Este Scaffold solo reserva espacio para la NavigationBar.
        // La barra de estado (barra del wifi, hora, notificaciones)
        // es gestionada por el Scaffold de CADA PANTALLA
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            if(mostrarNavigationBar) {
                NavigationBar {
                    NavigationBarItem(
                        selected = pantallaActual is SitesNav,
                        onClick = {
                            if (pantallaActual !is SitesNav) {
                                backStack.add(SitesNav)
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.Place,
                                contentDescription = "Icono de sitios"
                            )
                        },
                        label = {
                            Text(text = "Sitios")
                        }
                    )
                    NavigationBarItem(
                        selected = pantallaActual is PlansNav,
                        onClick = {
                            if (pantallaActual !is PlansNav) {
                                backStack.add(PlansNav)
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Outlined.EventNote,
                                contentDescription = "Icono de sitios"
                            )
                        },
                        label = {
                            Text(text = "Planes")
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavDisplay(
            modifier = Modifier.padding(innerPadding),
            backStack = backStack,
            onBack = volver,
            entryProvider = entryProvider {
                entry<SitesNav> {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        floatingActionButton = {
                            FloatingActionButton(
                                onClick = {
                                    Log.d("HOLA", "Click en ajustes")
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Settings,
                                    contentDescription = "Icono de ajustes"
                                )
                            }
                        }
                    ) { innerPadding ->
                        SitesRoute(
                            modifier = Modifier.padding(innerPadding),
                            viewModel = sitesViewModel,
                            onDetailNavigation = { sitioId ->
                                backStack.add(SiteDetailNav(siteId = sitioId))
                            }
                        )
                    }
                }

                entry<SiteDetailNav> { siteDetailNav ->

                    val uiState by sitesViewModel.uiState.collectAsStateWithLifecycle()

                    val id = siteDetailNav.siteId
                    val sitio = uiState.sitios.find { it.id == id }
                    SiteDetailScreen(sitio = sitio, volver = volver)
                }

                entry<PlansNav> {
                    PlansRoute(viewModel = plansViewModel)
                }

            }
        )
    }

}