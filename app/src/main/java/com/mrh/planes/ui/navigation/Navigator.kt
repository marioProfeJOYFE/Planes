package com.mrh.planes.ui.navigation

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.mrh.planes.ui.screen.SitesScreen

@Composable
fun Navigator() {
    // Crear el componente que se encarga de gestionar la
    // navegacion entre pantallas del proyecto
    // el ultimo elemento que añadamos a backStack
    // será el que veamos en la pantalla
    val backStack = rememberNavBackStack(SitesNav)

    // Funcion que elimina el ultimo destino de la lista de destinos
    val volver: () -> Unit = {
        if (backStack.size > 1) {
            backStack.removeLastOrNull()
        }
    }

    // Contenedor de el contenido de la aplicacion
    // Recibe como parametros el backStack (osease la lista que observa para saber
    // el destino actual y las rutas donde volver)
    // la accion al volver atras
    // y entryProvider, que es el lugar donde definimos la relacion entre los NavKeys
    // y lo que pintan en pantalla
    NavDisplay(
        backStack = backStack,
        onBack = {
            volver()
        },
        entryProvider = entryProvider {
            entry<SitesNav> {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                Log.d("HOLA", "Click en ajustes")
                                backStack.add(SettingsNav)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Settings,
                                contentDescription = "Icono de ajustes"
                            )
                        }
                    }
                ) { innerPadding ->
                    SitesScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }

            entry<SettingsNav>{
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        Text("Ajustes de la aplicacion")
                    }
                }
            }
        }
    )

}