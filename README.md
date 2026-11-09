# Memory: <título de tu juego>

<!--
  README NORMALIZADO · PROYECTO DE LA 1ª EVALUACIÓN

  Rellena cada apartado. Las indicaciones están en comentarios como este:
  BÓRRALAS cuando termines (no se ven al visualizar el README, pero no deben
  quedar en la entrega).

  Tienes un README de ejemplo, de un proyecto simulado, en ayuda/README-ejemplo.md.
  Sirve para ver QUÉ nivel de detalle se espera. No lo copies: este README
  tiene que explicar TU proyecto.

  Este README te servirá también para preparar el examen y la defensa:
  el apartado 5 es tu mapa del proyecto.
-->

## 1. Datos del proyecto

| | |
| :--- | :--- |
| **Autor/a** | <nombre y apellidos> |
| **Módulo / curso** | PMDM · 2º DAM · 2026/2027 |
| **Temática** | <una frase: de qué son las cartas> |

## 2. Temática y fuente de las imágenes

<!--
  Cuenta en 2-3 líneas qué temática has elegido.
  Después lista CADA imagen o recurso gráfico con su autoría y licencia.
  Solo valen imágenes propias o con licencia libre. Nada con derechos de terceros.
-->

<descripción de la temática>

| Imagen (fichero en `res/drawable`) | Autoría o fuente | Licencia |
| :--- | :--- | :--- |
| `<fichero>` | <hecha por mí / enlace> | <propia / CC0 / CC BY…> |

## 3. Captura o GIF

<!--
  Guarda la captura o el GIF dentro del proyecto (por ejemplo, en una carpeta docs/)
  y enlázalo. Tiene que verse el juego funcionando: tablero, cartas volteadas y contadores.
-->

![Mi juego funcionando](<ruta/a/tu-captura-o-gif>)

## 4. Cómo compilar, ejecutar y probar

<!-- Cualquiera debería poder hacerlo leyendo solo esto. Comprueba que funciona. -->

```bash
./gradlew assembleDebug          # compila la app
./gradlew testDebugUnitTest      # ejecuta los tests unitarios
```

<niveles que tiene tu juego y qué tamaño tiene cada uno>

## 5. Organización y estructura del proyecto

<!--
  ESTE ES EL APARTADO MÁS IMPORTANTE.
  Explica cómo está hecho TU proyecto, con los nombres reales de TUS ficheros y funciones.
  Si escribes algo que se podría aplicar a cualquier proyecto, no vale.
-->

### 5.1. Paquetes

<!-- El árbol de paquetes de tu proyecto, con una línea por paquete que diga para qué sirve. -->

```text
com.pm2627.p1ev.memory
├── <paquete>/   ← <para qué sirve>
├── <paquete>/   ← <para qué sirve>
└── <paquete>/   ← <para qué sirve>
```

### 5.2. Ficheros importantes

<!--
  Una fila por cada fichero relevante: dónde está el estado, dónde están las reglas
  del juego, dónde está la navegación, dónde está cada pantalla, dónde están los tests.
-->

| Fichero | Qué hace |
| :--- | :--- |
| `<Fichero.kt>` | <qué contiene y qué responsabilidad tiene> |
| `<Fichero.kt>` | <…> |

### 5.3. Cómo fluye un evento

<!--
  Elige un evento (por ejemplo, el usuario pulsa una carta) y cuenta su recorrido completo:
  desde la pantalla hasta que se vuelve a dibujar, nombrando TUS funciones y TUS clases
  en cada paso. Puedes acompañarlo de un esquema.
-->

1. <Quién recibe el toque y qué función llama>

2. <Qué evento llega al ViewModel y con qué datos>

3. <Qué hace el ViewModel con él>

4. <Cómo se actualiza el estado>

5. <Cómo llega a la pantalla y qué se vuelve a dibujar>

### 5.4. Decisiones de diseño

<!--
  Las decisiones que has tomado y por qué: dónde guardas el estado de cada carta,
  cómo controlas el temporizador, cómo bloqueas el tablero, qué pasas como argumento
  entre pantallas, etc. Sirve para recordarlas cuando te pregunten por ellas.
-->

- <decisión y motivo>

## 6. Mejoras opcionales

<!--
  Si no has hecho ninguna, escribe «Ninguna» y borra la tabla.
  Para cada mejora que SÍ incluyes, rellena una fila. Sin documentación no cuenta.
  Recuerda: las mejoras también hay que saber explicarlas en la defensa.
-->

| Mejora | API que usa | Fichero y función | Documentación seguida |
| :--- | :--- | :--- | :--- |
| <mejora> | <clase o función> | `<Fichero.kt>` · `<función>` | <enlace> |

## 7. Limitaciones conocidas

<!-- Lo que no funciona del todo o lo que mejorarías con más tiempo. Ser sincero suma. -->

- <limitación>

---

## Entrega

<!-- Borra esta parte al terminar. -->

Genera el zip con la tarea de la plantilla, **no comprimiendo la carpeta a mano**:

```bash
./gradlew crearZip -P nombre=apellido-nombre      # en Windows: gradlew.bat crearZip -Pnombre=apellido-nombre
```

El zip aparece en `entrega/` y ya deja fuera `build/`, `.gradle/`, `.idea/`, `local.properties`, las claves y la carpeta `ayuda/`. Descomprímelo en otra carpeta y comprueba que compila antes de entregarlo.
