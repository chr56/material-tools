plugins {
    // id("com.android.application") version "7.2.2" apply false
    id("com.android.library") version "7.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.6.21" apply false
}

tasks.create(name = "clean", type = Delete::class) {
    doLast { delete(rootProject.buildDir) }
}
val libVersion: String by extra("0.0.7-alpha01")
