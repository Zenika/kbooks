allprojects {
    buildscript {
        repositories {
            mavenCentral()
        }
    }

    repositories {
        mavenCentral()
    }
}

subprojects {
    group = "com.zenika"
    version = "1.1.0-SNAPSHOT"
}
