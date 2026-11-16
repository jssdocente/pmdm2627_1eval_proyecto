package com.pm2627.p1ev.memory.ui.tablero

import com.pm2627.p1ev.memory.model.Carta

/**
 * EL ESTADO DE LA PARTIDA: todo lo que pinta la pantalla del tablero.
 *
 * Todavía NO lo usa nadie: ahora mismo el estado vive dentro de TableroScreen, con
 * remember. Cuando lo saques de la pantalla, vivirá aquí, en un único objeto inmutable
 * que publica el TableroViewModel.
 *
 * Reglas:
 *   - data class con val: inmutable. Cada cambio crea un estado nuevo con copy(…).
 *   - es la ÚNICA fuente de verdad: si algo se ve en pantalla, sale de aquí.
 *
 * Apuntes: 3C.1 §2 (modelado inmutable del estado)
 */
data class TableroUiState(
    val cartas: List<Carta> = emptyList(),
    // TODO 5 (apartado 1.6): añade el resto del estado y, en el cuerpo, las propiedades calculadas.
)
