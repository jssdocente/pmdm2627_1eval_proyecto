package com.pm2627.p1ev.memory.ui.tablero

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * EL VIEWMODEL DE UNA PARTIDA: aquí vivirán el estado y las REGLAS del juego.
 *
 * Ya existe, pero todavía NO lo usa nadie: TableroScreen guarda su propio estado con
 * remember y por eso lo pierde al girar el móvil. El ViewModel sobrevive al giro.
 *
 *   - Guarda el estado en un MutableStateFlow PRIVADO y lo publica como StateFlow de
 *     solo lectura: la pantalla lo observa, pero no puede cambiarlo.
 *   - Cada cosa que hace el usuario será una función-evento (onAlgo) que publica un
 *     estado nuevo con _uiState.update { it.copy(…) }.
 *   - No importa nada de Compose: por eso se puede probar sin pantalla.
 *
 * Apuntes: 3C.1 §1 (qué es un ViewModel) y §3 (StateFlow y flujo unidireccional) · Sesión 3.05
 */
class TableroViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(TableroUiState())
    val uiState: StateFlow<TableroUiState> = _uiState.asStateFlow()

    // TODO (guía paso a paso, «Del remember al ViewModel»): reparte aquí las cartas
    // iniciales y añade el evento onCartaPulsada(id). Después, haz que TableroScreen
    // observe uiState en lugar de guardar su propio estado.
}
