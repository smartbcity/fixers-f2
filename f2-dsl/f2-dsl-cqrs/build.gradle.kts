plugins {
    id("city.smartb.fixers.gradle.kotlin.mpp")
    id("city.smartb.fixers.gradle.publish")
    kotlin("plugin.serialization")
//    id("dev.petuska.npm.publish")
}

dependencies {
    Dependencies.Mpp.kotlinxDatetime(::commonMainApi)
    Dependencies.Jvm.Spring.dataCommons(::jvmMainImplementation)

}
