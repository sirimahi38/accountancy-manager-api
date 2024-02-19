plugins {
    id("org.springframework.boot") version "3.0.5"
}

group = "com.ca.account.manager"
version = "0.0.1-SNAPSHOT"

tasks.bootRun {
    systemProperties.set("spring.profiles.active", "DEV")
}

tasks.withType<Jar>(){
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

springBoot {
    mainClass.set("com.ca.account.manager.CAAccountancyManager")
}

dependencies {
    implementation(project(":common"))
    implementation(project(":tasks"))
    implementation(project(":payments"))
    implementation(project(":security"))
    implementation(project(":reports"))
    implementation(project(":billing"))

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    annotationProcessor("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")

    compileOnly("com.google.cloud.tools:jib-gradle-plugin:3.3.1")

    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(group = "org.apache.tomcat.embed", module = "tomcat-embed-el")
    }

    implementation("org.postgresql:postgresql:42.6.0")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    this.mainClass.set("com.ca.account.manager.CAAccountancyManager")
    this.archiveFileName.set("accountancy-manager-0.0.1-SNAPSHOT.jar")
}

tasks.test {
    useJUnitPlatform()
}