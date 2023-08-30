import org.springframework.boot.gradle.tasks.bundling.BootJar

description = "Security Module"

tasks.getByName<BootJar>("bootJar") {
    enabled = true
}

tasks.getByName<Jar>("jar") {
    enabled = true
}

dependencies {
    implementation(project(":member"))
    implementation("org.springframework.boot:spring-boot-starter-security")
}
