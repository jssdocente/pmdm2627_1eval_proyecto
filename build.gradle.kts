// Archivo de build raíz: declara los plugins usados por los módulos, sin aplicarlos.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.jetbrains.kotlin.serialization) apply false
}

// ---------------------------------------------------------------------------
// Entrega: genera el zip del proyecto SIN lo que no debe ir (build, .gradle,
// .idea, local.properties, claves…). Uso:
//   ./gradlew crearZip -Pnombre=apellido-nombre        (Windows: gradlew.bat …)
// El zip se crea en la carpeta entrega/.
// ---------------------------------------------------------------------------
tasks.register<Zip>("crearZip") {
    group = "entrega"
    description = "Crea el zip de entrega del proyecto, sin los ficheros que no deben incluirse"

    val nombre = providers.gradleProperty("nombre").orElse("entrega")
    archiveFileName.set(nombre.map { "memory-$it.zip" })
    destinationDirectory.set(layout.projectDirectory.dir("entrega"))

    from(layout.projectDirectory) {
        // Se genera al compilar o es de tu IDE
        exclude("**/build/**", "**/.gradle/**", "**/.kotlin/**", "**/.idea/**", "**/*.iml")
        // Datos personales o privados
        exclude("**/local.properties", "**/*.jks", "**/*.keystore", "**/keystore.properties")
        // Material de ayuda, entregas anteriores y control de versiones
        exclude("ayuda/**", "entrega/**", ".git/**", "**/.DS_Store")
    }
    into(nombre.map { "memory-$it" })

    doLast {
        println("Zip creado: ${archiveFile.get().asFile}")
    }
}
