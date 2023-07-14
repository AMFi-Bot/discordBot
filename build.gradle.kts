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

val jdaVersion = "5.0.0-beta.12"
dependencies {
    implementation("ch.qos.logback:logback-classic:1.2.9")
    implementation("net.dv8tion:JDA:$jdaVersion")
    implementation("com.rabbitmq:amqp-client:5.16.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.+")
    implementation("redis.clients:jedis:4.3.1+")
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    implementation(kotlin("reflect"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
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