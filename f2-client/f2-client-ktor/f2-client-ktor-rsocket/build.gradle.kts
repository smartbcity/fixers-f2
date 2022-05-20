plugins {
	id("city.smartb.fixers.gradle.kotlin.mpp")
	id("city.smartb.fixers.gradle.publish")
//	id("dev.petuska.npm.publish")
	kotlin("plugin.serialization")
}
dependencies {
	commonMainApi(project(":f2-client:f2-client-core"))
	commonMainApi(project(":f2-dsl:f2-dsl-cqrs"))
	commonMainApi("io.rsocket.kotlin:rsocket-ktor-client:${Versions.rsocket}")

	jsMainApi("io.ktor:ktor-client-js:${Versions.Kotlin.ktor}")
	jvmMainImplementation("io.ktor:ktor-client-cio:${Versions.Kotlin.ktor}")
}
