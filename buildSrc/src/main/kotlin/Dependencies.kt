import city.smartb.gradle.dependencies.FixersDependencies
import city.smartb.gradle.dependencies.FixersPluginVersions
import city.smartb.gradle.dependencies.FixersVersions
import city.smartb.gradle.dependencies.Scope
import city.smartb.gradle.dependencies.add

object PluginVersions {
	val fixers = FixersPluginVersions.fixers
	val d2 = FixersPluginVersions.fixers
	const val kotlin = FixersPluginVersions.kotlin
	const val springBoot = FixersPluginVersions.springBoot
	const val npmPublish = FixersPluginVersions.npmPublish
}

object Versions {
	object Kotlin {
		const val ktor = FixersVersions.Kotlin.ktor
	}

	object Json {
		const val jackson = FixersVersions.Json.jacksonKotlin
	}

	object Spring {
		const val function = "4.0.0"
		const val boot = FixersVersions.Spring.boot
		const val data = FixersVersions.Spring.data
	}

	const val cucumber = FixersVersions.Test.cucumber
	const val springdoc = "1.6.11"
	const val rsocket = "0.15.4"
	const val embedMongo = "2.2.0"
	const val kotlinxDatetime = "0.4.0"
}

object Dependencies {
	object Mpp {
		fun rsocketKtorClient(scope: Scope) = scope.add(
			"io.rsocket.kotlin:rsocket-ktor-client:${Versions.rsocket}"
		)
		fun kotlinxDatetime(scope: Scope) = scope.add(
			"org.jetbrains.kotlinx:kotlinx-datetime:${Versions.kotlinxDatetime}"
		)
	}
	object Jvm {
		object Kotlin {
			fun coroutines(scope: Scope) = FixersDependencies.Jvm.Kotlin.coroutines(scope)
		}

		object Test {
			fun springTest(scope: Scope) =  scope.add(
				"org.springframework.boot:spring-boot-starter-test:${Versions.Spring.boot}"
			)
		}
		object Json {
			fun jackson(scope: Scope) = FixersDependencies.Jvm.Json.jackson(scope)
		}

		fun cucumber(scope: Scope) = FixersDependencies.Jvm.Test.cucumber(scope)

		object Spring {
			fun dataCommons(scope: Scope) = FixersDependencies.Jvm.Spring.dataCommons(scope)

			fun cloudFunctionDep(scope: Scope) = scope.add(
				"com.google.code.gson:gson:2.10.1",
				"io.cloudevents:cloudevents-spring:2.4.1",
				"com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2"
			)
			fun cloudFunction(scope: Scope) = scope.add(
				"org.springframework.cloud:spring-cloud-function-context:${Versions.Spring.function}",
				"org.springframework.cloud:spring-cloud-function-kotlin:${Versions.Spring.function}",
				"org.springframework.boot:spring-boot-autoconfigure:${Versions.Spring.boot}",
				"com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.Json.jackson}"
			)

			fun cloudFunctionWebflux(scope: Scope) = scope.add(
				"org.springframework.cloud:spring-cloud-starter-function-webflux:${Versions.Spring.function}"
			)

			fun cloudFunctionRSocket(scope: Scope) = scope.add(
				"org.springframework.cloud:spring-cloud-function-rsocket:${Versions.Spring.function}"
			)
		}
	}
}
