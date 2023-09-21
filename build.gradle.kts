plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "me.ssuamje"
version = "1.0-SNAPSHOT"
val kotestVersion = "5.7.2"
repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("MainKt")
}