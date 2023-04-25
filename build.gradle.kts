plugins {
    val AGPVersion = "7.4.2"
    id("com.android.library") version AGPVersion apply false
    id("org.jetbrains.kotlin.android") version "1.7.10" apply false
}

tasks.create(name = "clean", type = Delete::class) {
    doLast { delete(rootProject.buildDir) }
}
val libVersion: String by extra("0.0.9")
