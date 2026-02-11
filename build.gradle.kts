plugins {
    kotlin("jvm")
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

sourceSets {
    main {
        kotlin {
            srcDirs("src/main/kotlin")
        }
        java {
            srcDirs("src/main/java")
        }
    }
}

application {
    mainClass.set("org.example.MainKt")
}