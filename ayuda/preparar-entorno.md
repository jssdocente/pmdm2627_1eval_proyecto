# Preparar el entorno y abrir la plantilla

Esta guía explica cómo preparar tu equipo para abrir y compilar la plantilla del proyecto Memory, y qué hacer si algo falla.
La plantilla está preparada para abrirse en **IntelliJ IDEA** y compilar sin errores.

| Componente | Versión |
|---|---|
| IntelliJ IDEA | 2026.2 (con plugins *Android* y *Android Compose*) |
| Android Gradle Plugin (AGP) | 9.1.1 |
| Gradle | 9.3.1 (incluido en el proyecto mediante el *wrapper*) |
| Kotlin | 2.4.21 |
| JDK para Gradle | 21 |
| compileSdk / targetSdk / minSdk | 37 / 36 / 26 (Android 8.0) |
| Compose BOM | 2026.09.00 (Compose 1.12) |

> ⚠️ **No actualices las versiones** de AGP, Gradle ni de las librerías aunque el IDE lo sugiera.
> IntelliJ IDEA solo soporta hasta AGP 9.1, y las librerías más nuevas pueden exigir un
> `compileSdk` mayor que 37. Las versiones están fijadas en `gradle/libs.versions.toml`.

---

## 1. Requisitos (solo la primera vez)

1. **IntelliJ IDEA 2026.2** o posterior.
2. **Plugins de Android** en IntelliJ: *File > Settings > Plugins > Marketplace*, buscar e instalar
   **Android** y **Android Compose**. Reiniciar el IDE.
3. **SDK de Android con la plataforma 37**. Si ya tienes Android Studio instalado, puedes usar
   su SDK, que en Windows suele estar en `C:\Users\<usuario>\AppData\Local\Android\Sdk`.
   Si no tienes el SDK, puedes instalarlo desde IntelliJ (ver apartado 3).
4. **JDK 21**. Normalmente no hay que hacer nada (ver apartado 4).

## 2. Abrir el proyecto

1. Descomprime la plantilla en una **ruta corta y sin acentos, ñ ni espacios**, por ejemplo
   `C:\PMDM\memory`. Cambia el nombre de la carpeta si quieres.

   > ⚠️ **En Windows**, el plugin de Android **detiene la compilación** si la ruta del proyecto
   > tiene caracteres como `á`, `ñ` o `ç` (por ejemplo `C:\Users\Begoña\…` o `…\Programación\…`).
   > Evita también las carpetas sincronizadas con **OneDrive** (como *Documentos* o *Escritorio*),
   > porque bloquean archivos durante la compilación y alargan la ruta. Windows tiene un límite de
   > 260 caracteres de ruta, y las carpetas internas de compilación son muy largas.
2. En IntelliJ: *File > Open…* y selecciona la **carpeta del proyecto**, la que contiene
   `settings.gradle.kts`. No elijas `New project from existing sources`.
3. Si pregunta si confías en el proyecto, pulsa **Trust Project**.
4. Espera a que termine la sincronización de Gradle (barra inferior). La primera vez tarda
   varios minutos porque descarga Gradle y las librerías.
5. Ejecuta la app con la configuración **app** y el botón ▶, eligiendo un emulador o un móvil.

## 3. Ruta del SDK de Android

Gradle busca el SDK en este orden:

1. **`local.properties`** en la raíz del proyecto, con la línea `sdk.dir=…`.
   IntelliJ lo crea automáticamente con la ruta del SDK configurada en el IDE.
   **Este archivo es personal de cada ordenador: no lo compartas ni lo subas a Git.**
2. La **variable de entorno `ANDROID_HOME`**.

### Configurarlo en IntelliJ
*File > Settings > Languages & Frameworks > Android SDK*: indica la ruta del SDK, o pulsa
*Edit* para descargarlo. En la pestaña **SDK Platforms** marca **API 37**.
Si no está instalada, Gradle la descarga automáticamente en la primera compilación (necesita internet).

### Configurarlo a mano
Crea `local.properties` en la raíz del proyecto. En Windows, usa barras `/`:

```properties
sdk.dir=C:/Users/<usuario>/AppData/Local/Android/Sdk
```

### Configurarlo con una variable de entorno (útil para usar el terminal)
En Windows, abre *Editar las variables de entorno del sistema > Variables de entorno… > Nueva*
(de usuario): nombre `ANDROID_HOME`, valor `C:\Users\<usuario>\AppData\Local\Android\Sdk`.
Después, reinicia IntelliJ y los terminales.

## 4. JDK que usa Gradle

El proyecto indica que Gradle debe ejecutarse con **JDK 21** en el archivo
`gradle/gradle-daemon-jvm.properties`. Gradle lo resuelve solo:

1. Busca un JDK 21 instalado en el equipo: `JAVA_HOME`, las carpetas de instalación
   habituales (por ejemplo, `C:\Program Files\Eclipse Adoptium`) y el registro de Windows.
2. Si no lo encuentra, **lo descarga automáticamente** (plugin *foojay*, necesita internet).

> ⚠️ Como el proyecto incluye `gradle/gradle-daemon-jvm.properties`, Gradle **ignora**
> `org.gradle.java.home` y el *Gradle JVM* elegido en IntelliJ: siempre usa un JDK 21.
> Para usar un JDK concreto hay que decirle **dónde buscarlo** (ver abajo).

### Si la descarga automática falla (sin internet, proxy del centro…)
1. Instala un **JDK 21**. Por ejemplo:
   - Con el instalador de Eclipse Temurin 21 (adoptium.net). Gradle lo encuentra solo.
   - Desde IntelliJ: *File > Settings > Build, Execution, Deployment > Build Tools > Gradle >
     Gradle JVM > Download JDK…*, versión **21**. IntelliJ lo guarda en `C:\Users\<usuario>\.jdks\`.
2. Si Gradle sigue sin encontrarlo, indícale la carpeta del JDK en el archivo de configuración
   **de tu usuario**, no del proyecto: `C:\Users\<usuario>\.gradle\gradle.properties`
   (créalo si no existe). Para indicar varias carpetas, sepáralas con comas:

   ```properties
   org.gradle.java.installations.paths=C:/Users/<usuario>/.jdks/temurin-21.0.5
   ```
3. Vuelve a sincronizar Gradle (icono 🐘 *Sync*).

> No pongas rutas de tu ordenador en el `gradle.properties` **del proyecto**, porque el
> proyecto dejaría de funcionar en los demás ordenadores.

### Si estás detrás de un proxy
Añade también al `C:\Users\<usuario>\.gradle\gradle.properties`:

```properties
systemProp.http.proxyHost=proxy.centro.es
systemProp.http.proxyPort=8080
systemProp.https.proxyHost=proxy.centro.es
systemProp.https.proxyPort=8080
```

## 5. Usarlo desde el terminal

```bash
gradlew.bat assembleDebug        # Compila la app (en macOS/Linux: ./gradlew assembleDebug)
gradlew.bat test                 # Ejecuta los tests unitarios
gradlew.bat installDebug         # Instala la app en el emulador o móvil conectado
gradlew.bat connectedAndroidTest # Ejecuta los tests instrumentados en el emulador
gradlew.bat crearZip -Pnombre=apellido-nombre   # Crea el zip de entrega en entrega/
```

## 6. Estructura de la plantilla

```
memory/
├── app/
│   ├── build.gradle.kts            ← módulo: SDK y dependencias (ya incluye Navigation 3, ViewModel y tests)
│   └── src/
│       ├── main/
│       │   ├── AndroidManifest.xml
│       │   ├── java/com/pm2627/p1ev/memory/   ← el paquete ya creado: aquí va tu código
│       │   │   ├── MainActivity.kt
│       │   │   └── ui/theme/                  ← colores, tipografía y tema Material 3
│       │   └── res/                           ← textos, iconos, imágenes
│       ├── test/java/com/pm2627/p1ev/memory/  ← tests unitarios (JVM)
│       └── androidTest/                       ← tests instrumentados (emulador)
├── ayuda/                          ← esta guía y el README de ejemplo (no van en el zip)
├── gradle/libs.versions.toml       ← versiones de plugins y librerías (no las actualices)
├── README.md                       ← el que TÚ rellenas
└── build.gradle.kts                ← incluye la tarea crearZip
```

El paquete `com.pm2627.p1ev.memory` **ya está creado y no hay que cambiarlo**.

## 7. Errores frecuentes

| Error | Solución |
|---|---|
| `Your project path contains non-ASCII characters` | Mueve el proyecto a una ruta sin acentos ni `ñ`, por ejemplo `C:\PMDM\` (apartado 2). |
| Errores con rutas muy largas, `Filename too long` o archivos bloqueados al compilar | Mueve el proyecto a una ruta corta y fuera de OneDrive, por ejemplo `C:\PMDM\`. |
| Falla la descarga del JDK o de Gradle (`Connection refused`, `timed out`, `PKIX…`) | La red del centro bloquea las descargas. Configura el proxy o instala JDK 21 a mano (apartado 4). Hay que tener acceso a `services.gradle.org`, `api.foojay.io`, `github.com`, `dl.google.com` y `repo.maven.apache.org`. |
| `SDK location not found. Define a valid SDK location with an ANDROID_HOME environment variable or by setting the sdk.dir path…` | Configura el SDK (apartado 3). |
| `Failed to find target with hash string 'android-37'` o `platforms;android-37` no instalado | Instala la **API 37** en *Settings > Languages & Frameworks > Android SDK > SDK Platforms*. |
| `Cannot find a Java installation… matching… languageVersion=21` o fallo al descargar el JDK | Instala un JDK 21 o indica su carpeta con `org.gradle.java.installations.paths` (apartado 4). |
| `The project is using an incompatible version (AGP 9.x.x) of the Android Gradle plugin` | Alguien ha actualizado AGP. Vuelve a poner `agp = "9.1.1"` en `gradle/libs.versions.toml`. |
| Aviso `This version only understands SDK XML versions up to 3 but an SDK XML file of version 4 was encountered` | Es solo un aviso: el SDK es más reciente que AGP 9.1. Se puede ignorar. |
| `Dependency '…' requires … compileSdk of at least …` | Se han actualizado librerías a versiones que exigen un SDK más nuevo. Recupera las versiones originales de `gradle/libs.versions.toml`. |
| `Plugin [id: 'com.android.application'] was not found` o el IDE no reconoce el proyecto Android | Instala los plugins **Android** y **Android Compose** (apartado 1) y reinicia el IDE. |
| `The 'org.jetbrains.kotlin.android' plugin is no longer required…` | Desde AGP 9 Kotlin viene incluido: quita esa línea de los `build.gradle.kts`. |
| `Unresolved reference 'kotlinOptions'` | Usa el bloque `kotlin { compilerOptions { … } }` como en `app/build.gradle.kts`. |
| La vista previa (`@Preview`) no se muestra | Compila el proyecto (*Build > Build Project*) y pulsa *Refresh* en el panel de la vista previa. |
| `./gradlew: Permission denied` (macOS/Linux) | Ejecuta `chmod +x gradlew`. |

Si después de cambiar algo el proyecto sigue fallando: *File > Invalidate Caches… > Invalidate
and Restart*, y vuelve a sincronizar Gradle.
