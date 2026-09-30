package com.mrh.planes.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mrh.planes.ui.component.SiteCard

@Composable
fun SitesScreen(modifier: Modifier = Modifier){
    Column(
        modifier = modifier
    ){
        SiteCard(
            nombre = "Colegio JOYFE",
            direccion = "C. Vital Aza 65",
            valoracion = 3.4
        )
        SiteCard(
            nombre = "Colegio JOYFE",
            direccion = "C. Vital Aza 65",
            valoracion = 3.4
        )
        SiteCard(
            nombre = "Colegio JOYFE",
            direccion = "C. Vital Aza 65",
            valoracion = 3.4
        )
    }
}


@Composable
@Preview
fun SitesScreenPreview(){
    SitesScreen()
}