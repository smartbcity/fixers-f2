plugins {
    id("city.smartb.fixers.gradle.kotlin.mpp")
    id("city.smartb.fixers.gradle.publish")
    id("city.smartb.fixers.gradle.npm")
    kotlin("plugin.serialization")
}

dependencies {
    Dependencies.Mpp.kotlinxDatetime(::commonMainApi)
    Dependencies.Jvm.Spring.dataCommons(::jvmMainImplementation)

}
