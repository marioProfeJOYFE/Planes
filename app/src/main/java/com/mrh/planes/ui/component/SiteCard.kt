package com.mrh.planes.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mrh.planes.data.Site
import com.mrh.planes.ui.theme.PlanesTheme

@Composable
fun SiteCard(
    sitio: Site,
    onFavoritoClick: () -> Unit,
    onClick: (Int) -> Unit
) {
    Card(
        onClick = {
            onClick(sitio.id)
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Icono del sitio
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.LocationOn,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(26.dp)
                )
            }

            // Información: ocupa el espacio disponible.
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = sitio.nombre,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = sitio.direccion,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = "Valoración: ${sitio.rating} / 5",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            // La estrella comunica la acción a la pantalla.
            IconButton(
                onClick = onFavoritoClick
            ) {
                Icon(
                    imageVector = if (sitio.esFavorito) {
                        Icons.Rounded.Star
                    } else {
                        Icons.Rounded.StarBorder
                    },
                    contentDescription = if (sitio.esFavorito) {
                        "Quitar ${sitio.nombre} de favoritos"
                    } else {
                        "Añadir ${sitio.nombre} a favoritos"
                    },
                    tint = if (sitio.esFavorito) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SiteCardPreview() {
    PlanesTheme {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SiteCard(
                sitio = Site(
                    id = 0,
                    nombre = "Colegio JOYFE",
                    direccion = "Calle Vital Aza 65",
                    rating = 4.5,
                    esFavorito = true
                ),
                onFavoritoClick = {},
                onClick = {}
            )

            SiteCard(
                sitio = Site(
                    id = 1,
                    nombre = "Colegio San Blas",
                    direccion = "Calle San Blas",
                    rating = 3.5,
                    esFavorito = false
                ),
                onFavoritoClick = {},
                onClick = {}
            )
        }
    }
}