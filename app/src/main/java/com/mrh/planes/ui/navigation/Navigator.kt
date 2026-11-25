package com.mrh.planes.ui.navigation

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import com.mrh.planes.ui.screen.SitesScreen
import com.mrh.planes.ui.viewmodel.PlansViewModel
import com.mrh.planes.ui.viewmodel.SitesViewModel


@Composable
fun Navigator(){
    val backStack = rememberNavBackStack(SitesNav)

    val volver = {
        if(backStack.size > 1){
            backStack.removeLastOrNull()
        }
    }

    val sitesViewModel : SitesViewModel = viewModel()

    val plansViewModel : PlansViewModel = viewModel()

    NavDisplay(
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