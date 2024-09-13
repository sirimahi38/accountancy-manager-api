plugins {
    `java-library`
}


tasks.test {
    useJUnitPlatform()
}

dependencies{
    runtimeOnly("org.postgresql:postgresql")

    api("org.springframework.boot:spring-boot-starter-data-jpa")
    annotationProcessor("org.springframework.boot:spring-boot-starter-data-jpa")

    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.testcontainers:testcontainers")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")

}
