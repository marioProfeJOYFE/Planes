package com.mrh.planes.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.mrh.planes.data.Site
import com.mrh.planes.ui.component.SiteCard

@Composable
fun SitesScreen(modifier: Modifier = Modifier) {

    //DATOS QUE MUESTRA LA INTERFAZ

    // Variable para buscar en la lista de sitios (escribiendo en la caja de texto)
    var textoBusqueda by remember { mutableStateOf("") }

    var isCheckBoxFavPresionado by remember { mutableStateOf(false) }

    var isSwitchFavPresionado by remember { mutableStateOf(true) }

    var sliderValue by remember { mutableStateOf(0f) }

    // LAMBDA que filtrará la lista, tanto por el [textoBusqueda]
    // como por [isCheckBoxFavPresionado]
    val listaFiltrada = { listaSitios: List<Site>, esPresionado: Boolean ->
        var listaFinal = listOf<Site>()
        if(esPresionado){
            listaFinal = listaSitios.filter { sitio : Site ->
                //sitio.esFavorito == true Y (sitio.nombre o sitio.direccion contengan textoBusqueda == true)
                sitio.esFavorito &&
                        //PARENTESIS ENTRE AMBAS CONDICIONES
                        (sitio.nombre.contains(textoBusqueda, ignoreCase = true) ||
                        sitio.direccion.contains(textoBusqueda, ignoreCase = true))
            }
        }else {
            listaFinal = listaSitios.filter { sitio : Site ->
                        sitio.nombre.contains(textoBusqueda, ignoreCase = true) ||
                        sitio.direccion.contains(textoBusqueda, ignoreCase = true)
            }
        }
        // NO HAY RETURN EXPLICITO ->
        // directamente se referencia la variable o valor
        listaFinal
    }

    // Lista con los sitios que mostraremos en la columna
    val listaSitios = listOf<Site>(
        Site(
            nombre = "Colegio JOYFE",
            direccion = "Calle Vital Aza 65",
            esFavorito = true
        ),
        Site(
            nombre = "Colegio San Blas",
            direccion = "Calle San Blas",
            rating = 3.5
        ),
        Site(
            nombre = "Colegio Rivers",
            direccion = "Calle Vital Pepe 67",
            esFavorito = true
        )
    )

    // INTERFAZ
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // item -> Elemento en PARTICULAR, SOLO UNO
        item {
            TextField(
                value = textoBusqueda,
                onValueChange = { textoUsuario ->
                    textoBusqueda = textoUsuario
                },
                maxLines = 1,
                // TODO: Acciones a definir en el teclado
                keyboardActions = KeyboardActions(
                    // TODO: Definir que hacer
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search,
                    keyboardType = KeyboardType.Text
                )
            )
        }

        // Elemento que contendrá el ChechBox y un texto
        item{
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    onCheckedChange = {
                        isCheckBoxFavPresionado = !isCheckBoxFavPresionado
                    },
                    checked = isCheckBoxFavPresionado,
                    enabled = isSwitchFavPresionado
                )
                Text(text = "Filtrar Favoritos")
            }
        }

        //Elemento que contendrá el Switch que habilitará el Checkbox
        item{
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Switch(
                    checked = isSwitchFavPresionado,
                    onCheckedChange = {
                        isSwitchFavPresionado = !isSwitchFavPresionado
                        isCheckBoxFavPresionado = false
                    }
                )
                Text(text = "Habilitar Checkbox")
            }

        }

        //Estamos pintando los elementos de la lista, pero, al tener la llamada a filter,
        // solo pintaremos los elementos que cumplan la condicion
        // En este caso la condicion es que el textoBusqueda
        // contenga caracteres del nombre o direccion de un sitio
        items(
            listaFiltrada(listaSitios,isCheckBoxFavPresionado)
        ) { sitio: Site ->
            SiteCard(sitio = sitio)
        }

        item{
            Slider(
                value = sliderValue,
                onValueChange = { valorUsuario ->
                    sliderValue = valorUsuario
                },
                valueRange = 0f..5f,
                steps = 5
            )
            Text(text = sliderValue.toString())
        }
    }
}


@Composable
@Preview
fun SitesScreenPreview() {
    SitesScreen()
}