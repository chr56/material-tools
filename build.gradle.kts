plugins {
    val AGPVersion = "8.2.2"
    id("com.android.library") version AGPVersion apply false
    val kotlinVersion = "1.9.22"
    id("org.jetbrains.kotlin.android") version kotlinVersion apply false
}

tasks.create(name = "clean", type = Delete::class) {
    doLast { delete(rootProject.layout.buildDirectory) }
}

val libVersion: String by extra("0.2.0")
