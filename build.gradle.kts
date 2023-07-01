plugins {
    val AGPVersion = "8.0.2"
    id("com.android.library") version AGPVersion apply false
    val kotlinVersion = "1.8.10"
    id("org.jetbrains.kotlin.android") version kotlinVersion apply false
}

tasks.create(name = "clean", type = Delete::class) {
    doLast { delete(rootProject.buildDir) }
}
val libVersion: String by extra("0.1.0")
