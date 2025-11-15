plugins {
    kotlin("jvm") version "2.2.20"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
val ktor_version: String by project
val logback_version: String by project

dependencies {
    implementation("com.newrelic.agent.java:newrelic-api:8.14.0")
    implementation("ch.qos.logback:logback-classic:${logback_version}")
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("io.ktor:ktor-client-java:${ktor_version}")
    implementation("io.ktor:ktor-client-jetty:${ktor_version}")
    implementation("io.ktor:ktor-client-okhttp:${ktor_version}")
    implementation("ch.qos.logback:logback-classic:${logback_version}")
    implementation("io.ktor:ktor-client-apache:3.3.2")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}