import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
    kotlin("jvm") version "1.8.21"
    application
    java
}

group = "org.amfibot.discord"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val jdaVersion = "5.0.0-beta.11"
dependencies {
    implementation("net.dv8tion:JDA:$jdaVersion")
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

application {
    mainClass.set("org.amfibot.discord.bot.MainKt")
}

tasks.jar {
    manifest {
        attributes("Main-Class" to application.mainClass)
    }
}