plugins {
    `java-library`
}

dependencies{
    runtimeOnly("org.postgresql:postgresql")

    api("org.springframework.boot:spring-boot-starter-data-jpa")
    annotationProcessor("org.springframework.boot:spring-boot-starter-data-jpa")
}
