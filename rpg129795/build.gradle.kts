plugins {
    id("java")
    kotlin("jvm")

    application
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "it.unicam.cs.mpgc.rpg129795"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.13.2")
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(25)
}

javafx {
    version = "24.0.1"
    modules = listOf("javafx.controls", "javafx.fxml")
}

application {
    mainClass.set("it.unicam.cs.mpgc.rpg129795.gui.RpgApplication")
}