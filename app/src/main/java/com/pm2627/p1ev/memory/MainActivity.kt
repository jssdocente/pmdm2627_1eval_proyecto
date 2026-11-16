package com.pm2627.p1ev.memory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.pm2627.p1ev.memory.ui.tablero.TableroScreen
import com.pm2627.p1ev.memory.ui.theme.MemoryTheme

/**
 * LA ACTIVITY: solo arranca la app.
 *
 * De momento muestra directamente la pantalla del tablero. Cuando haya tres pantallas
 * (apartado 3.3 de la guía), aquí se pintará MemoryApp, con la pila de navegación.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MemoryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TableroScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
