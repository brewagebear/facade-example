plugins {
    id("java")
    id("org.springframework.boot") version "3.1.3" apply false
    id("io.spring.dependency-management") version "1.1.3" apply false
}

repositories {
    mavenCentral()
}

allprojects {
    group = "io.github.brewagebear"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin("java")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    val implementation by configurations

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web")
    }
}
