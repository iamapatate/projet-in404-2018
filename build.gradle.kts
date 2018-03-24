buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath("org.junit.platform:junit-platform-gradle-plugin:1.0.3")
    }
}

plugins {
    java
    application
}

apply {
    plugin("org.junit.platform.gradle.plugin")
}

application {
    mainClassName = "fr.uvsq.poo.monprojet.Application"
}

dependencies {
    // Logging
    compile("org.apache.logging.log4j:log4j-api:2.10.0")
    compile("org.apache.logging.log4j:log4j-core:2.10.0")
    compile("com.fasterxml.jackson.core:jackson-databind:2.9.4")
    compile("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.9.4")

    // Pour les tests unitaires
    testCompile("org.junit.jupiter:junit-jupiter-api:5.0.3")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:5.0.3")
    testRuntime("org.junit.jupiter:junit-jupiter-params:5.0.3")
    testCompile("org.hamcrest:hamcrest-all:1.3")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    jcenter()
}
