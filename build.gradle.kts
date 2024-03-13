plugins {

}

tasks.create(name = "clean", type = Delete::class) {
    doLast { delete(rootProject.layout.buildDirectory) }
}

val libVersion: String by extra("0.2.0")
