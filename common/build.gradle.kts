plugins {
    java
}


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    annotationProcessor("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.fasterxml.jackson.core:jackson-annotations")
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("com.fasterxml.jackson.core:jackson-core")

    runtimeOnly("org.postgresql:postgresql")

    testRuntimeOnly("org.postgresql:postgresql") {
        because("tests need the Postgres JDBC driver")
    }

    testImplementation("org.flywaydb:flyway-core")
    testImplementation("org.flywaydb:flyway-database-postgresql")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.3.Final")
    testImplementation("org.mapstruct:mapstruct:1.5.3.Final")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.testcontainers:testcontainers")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}