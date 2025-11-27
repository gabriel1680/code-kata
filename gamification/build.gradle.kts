plugins {
    kotlin("jvm") version "2.0.0"
    application
}

group = "org.gbl"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    testImplementation(kotlin("test"))
    testImplementation("org.mockito.kotlin:mockito-kotlin:6.1.0")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("org.gbl.MainKt")
}

tasks.register<JavaExec>("runCli") {
    group = "application"
    description = "Runs CLI App"
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("checkin.in.cli.MainKt")
}