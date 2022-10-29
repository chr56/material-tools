plugins {
    // id("com.android.application") version "7.2.2" apply false
    id("com.android.library") version "7.3.0" apply false
    id("org.jetbrains.kotlin.android") version "1.7.10" apply false
}

tasks.create(name = "clean", type = Delete::class) {
    doLast { delete(rootProject.buildDir) }
}
val libVersion: String by extra("0.0.8")
