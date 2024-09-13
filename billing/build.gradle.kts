plugins {
    java
}

dependencies {
    implementation(project(":common"))
}


tasks.test {
    useJUnitPlatform()
    maxParallelForks = 1
}