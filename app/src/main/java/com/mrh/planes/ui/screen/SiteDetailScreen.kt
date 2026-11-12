package com.mrh.planes.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mrh.planes.data.Site

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SiteDetailScreen(
    modifier: Modifier = Modifier,
    sitio: Site?,
    volver: () -> Unit
)
{
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    sitio?.nombre?.let {
                            textoNombre -> Text(text = textoNombre)
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            volver()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).fillMaxSize()
        ) {
            sitio?.nombre?.let { Text(it) }
        }

    }
}