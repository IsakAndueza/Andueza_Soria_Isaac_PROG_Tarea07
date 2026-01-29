plugins {
    id("java")
}

group = "es.isak.dam"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation(files("libs/entrada-1.0.3.jar"))
}

tasks.test {
    useJUnitPlatform()
}