plugins {
    java
    id("io.spring.dependency-management") version "1.1.0"
}

group = "com.ca.account.manager"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

allprojects{
    repositories {
        mavenCentral()
        google()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
}

sourceSets {
    create("integration-test") {
        compileClasspath += sourceSets.main.get().output
        runtimeClasspath += sourceSets.main.get().output
    }
}

val integrationTestImplementation: Configuration by configurations.getting {
    extendsFrom(configurations.implementation.get())
}


configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

subprojects {

    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "java")

    dependencies {
        implementation("org.slf4j:slf4j-api")

        //Security
        implementation("org.springframework.boot:spring-boot-starter-security")

        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")

        //AOP
        implementation("org.springframework.boot:spring-boot-starter-aop")

        //common
        implementation("commons-io:commons-io:2.11.0")
        implementation("org.apache.commons:commons-lang3:3.12.0")
        implementation("com.google.guava:guava:23.0")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("com.github.tomakehurst:wiremock:3.0.0-beta-8")
        testImplementation("org.assertj:assertj-core")
        testImplementation("org.mockito:mockito-inline")
        testImplementation("org.mockito:mockito-core:5.3.1")
        testImplementation("org.mockito:mockito-junit-jupiter:5.3.1")
        testImplementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.security:spring-security-test")

        //API Documentation
        implementation("io.swagger:swagger-annotations:1.6.10")
        implementation("io.swagger.core.v3:swagger-annotations:2.2.8")

        //Fixtures
        testImplementation("com.github.javafaker:javafaker:1.0.2")

        //Test containers
        testImplementation("org.testcontainers:postgresql:1.18.1")
        testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.2")
        testImplementation("org.testcontainers:junit-jupiter:1.18.1")
        testImplementation("org.testcontainers:testcontainers-bom:1.18.1")
    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }

}
