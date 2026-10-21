package com.mrh.planes.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mrh.planes.data.Site

@Composable
fun SiteCard(
    sitio: Site,
    onFavoritoClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 6.dp,
                    vertical = 8.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column() {
                Text(text = sitio.nombre)
                Text(text = sitio.direccion)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = sitio.rating.toString())
                IconButton(
                    //IGUAL DE VALIDO (SOLO UNA ACCION)
                    //onClick = onFavoritoClick
                    onClick = {
                        onFavoritoClick()
                    }
                ) {
                    Icon(
                        imageVector = if (sitio.esFavorito) Icons.Rounded.Star else Icons.Rounded.StarBorder,
                        contentDescription = "Icono de Estrella",
                        tint = if(sitio.esFavorito) Color.Red else Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun SiteCardPreview() {
    SiteCard(
        sitio = Site(
            nombre = "Colegio JOYFE",
            direccion = "Calle Vital Aza 65",
            esFavorito = true
        ),
        onFavoritoClick = {
            //DEFINIMOS LA ACCION
        }
    )
}