package com.mrh.planes.data

/**
 * Clase Site
 *
 * @author Mario Ríos
 *
 * @param nombre Nombre del lugar
 * @param direccion Direccion del lugar
 * @param rating Valoracion del lugar
 * @param esFavorito Marca de favorito del usuario
 *
 *
 */
data class Site(
    val nombre: String,
    val direccion: String,
    val rating: Double = 1.0,
    var esFavorito: Boolean = false
)
