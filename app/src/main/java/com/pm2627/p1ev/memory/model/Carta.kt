package com.pm2627.p1ev.memory.model

/**
 * UNA CARTA DEL TABLERO · versión de PARTIDA
 *
 * Es un dato inmutable (data class con val): para cambiar una carta no se modifica,
 * se crea otra con copy(…).
 *
 * De momento solo sabe su id y si está boca arriba. Te sirve para tener algo que
 * pintar desde el primer día. En la guía paso a paso (apartado 1.4) la completarás:
 * qué dibujo muestra, cómo se sabe que dos cartas son pareja y en qué situación está
 * (¿boca abajo, boca arriba, emparejada? Un Boolean no basta para tres situaciones).
 *
 * Apuntes: Kotlin · data class y copy (23-data-classes)
 */
data class Carta(
    val id: Int,
    val bocaArriba: Boolean = false,
    // TODO 2 (apartado 1.4): añade el dibujo y la pareja, y cambia bocaArriba por la situación.
)
