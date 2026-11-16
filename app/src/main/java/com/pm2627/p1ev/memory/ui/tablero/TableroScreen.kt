package com.pm2627.p1ev.memory.ui.tablero

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pm2627.p1ev.memory.model.Carta
import com.pm2627.p1ev.memory.ui.theme.MemoryTheme

/**
 * LA PANTALLA DEL TABLERO · PUNTO DE PARTIDA
 *
 * Esto ya funciona: seis casillas lisas en una cuadrícula y un contador de toques.
 * Al tocar una casilla se da la vuelta y enseña su número; si la vuelves a tocar,
 * se pone boca abajo. Todavía no hay parejas, ni reglas, ni imágenes.
 *
 * Pero está hecho MAL a propósito: el estado (las cartas y los toques) vive AQUÍ,
 * dentro de la pantalla, con remember. Prueba esto:
 *   1. Ejecuta la app, da la vuelta a dos o tres casillas.
 *   2. Gira el móvil (o el emulador).
 *   3. ¿Qué ha pasado con las casillas y el contador? ¿Por qué?
 *
 * Ese es el punto de partida de la guía paso a paso: sacar el estado de la pantalla
 * y llevarlo al TableroViewModel (que ya existe y todavía no usa nadie).
 *
 * Apuntes: Compose · estado (22-state-management) · 3C.1 §1.1 (cambios de configuración)
 */
@Composable
fun TableroScreen(modifier: Modifier = Modifier) {
    // ⚠️ El estado de la partida, dentro de la pantalla: se pierde al girar el móvil.
    var cartas by remember { mutableStateOf(List(6) { Carta(id = it) }) }
    var toques by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text("Toques: $toques", style = MaterialTheme.typography.titleLarge)

        // Casillas de al menos 100 dp: 3 columnas en vertical y todas en una fila en horizontal.
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 100.dp),
            modifier = Modifier.widthIn(max = 720.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(cartas) { carta ->
                Casilla(
                    carta = carta,
                    onClick = {
                        // La lista es inmutable: se crea una lista NUEVA con esa carta cambiada.
                        cartas = cartas.map {
                            if (it.id == carta.id) it.copy(bocaArriba = !it.bocaArriba) else it
                        }
                        toques++
                    },
                )
            }
        }
    }
}

/** Una casilla lisa: «?» boca abajo y su número boca arriba. */
@Composable
private fun Casilla(carta: Carta, onClick: () -> Unit) {
    Card(
        modifier = Modifier.aspectRatio(3f / 4f).clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = if (carta.bocaArriba) MaterialTheme.colorScheme.secondaryContainer
            else MaterialTheme.colorScheme.primary,
        ),
    ) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = if (carta.bocaArriba) "${carta.id + 1}" else "?",
                style = MaterialTheme.typography.headlineMedium,
                color = if (carta.bocaArriba) MaterialTheme.colorScheme.onSecondaryContainer
                else MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TableroScreenPreview() {
    MemoryTheme {
        TableroScreen()
    }
}
