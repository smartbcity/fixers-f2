import city.smartb.gradle.dependencies.FixersVersions

plugins {
	id("city.smartb.fixers.gradle.kotlin.jvm")
}

dependencies {
	Dependencies.Jvm.cucumber(::api)
}