plugins {
    java
    id("org.springframework.boot") version "3.0.5"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "com.ca.account.manager"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}


sourceSets {
    create("integration-test") {
        compileClasspath += sourceSets.main.get().output
        runtimeClasspath += sourceSets.main.get().output
    }
}

val integrationTestImplementation by configurations.getting {
    extendsFrom(configurations.implementation.get())
}


configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")

    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")

    annotationProcessor("org.springframework.boot:spring-boot-starter-data-jpa")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.h2database:h2")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    testImplementation("org.junit.jupiter:junit-jupiter:5.7.1")
    testImplementation("junit:junit")
    testImplementation("org.assertj:assertj-core")
    testImplementation("org.mockito:mockito-inline")
    testImplementation("org.springframework:spring-test")
    testImplementation("org.glassfish:jakarta.el:4.0.2")

    integrationTestImplementation("junit:junit")
    integrationTestImplementation("io.rest-assured:rest-assured:5.2.1")
    integrationTestImplementation("org.springframework.boot:spring-boot-starter-test")

    testRuntimeOnly("org.glassfish:jakarta.el:4.0.2")

}

tasks.withType<Test> {
    useJUnitPlatform()
}

