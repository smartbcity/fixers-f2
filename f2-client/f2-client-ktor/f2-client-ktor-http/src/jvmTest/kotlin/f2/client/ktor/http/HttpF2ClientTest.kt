package f2.client.ktor.http

import f2.client.ktor.http.model.F2FilePart
import f2.client.ktor.http.plugin.F2Auth
import f2.client.ktor.http.plugin.model.AuthRealmPassword
import f2.client.ktor.http.server.ServerClient
import f2.client.ktor.http.server.command.ServerConsumeCommand
import f2.client.ktor.http.server.command.ServerUploadCommand
import f2.client.ktor.http.server.command.ServerUploadCommandBody
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.jvm.javaio.toByteReadChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.core.io.support.PathMatchingResourcePatternResolver

class HttpF2ClientTest {

//	@Test
	fun auth(): Unit = runTest {
		val client = HttpClient {
			install(ContentNegotiation) {
				json(Json {
					ignoreUnknownKeys = true
				})
			}

			install(F2Auth) {
				getAuth = {
					AuthRealmPassword(
						serverUrl = "https://dev.app.alveoleplus.fr/auth",
						realmId = "alveole-dev",
						redirectUrl = "",
						clientId = "alveole-web",
						username = "admin",
						password = "passw0rd"
					)
				}
			}
		}

		val organizationId = "7e1b2264-a856-4429-a4b5-6f2653bedb65"
		val result = client.post("https://dev.app.alveoleplus.fr/api/organizationGet") {
			header("Content-Type", ContentType.Application.Json)
			setBody(mapOf("id" to organizationId))
		}.bodyAsText()

		println(result)
		Assertions.assertThat(result).contains(organizationId)
	}

//	@Test
	fun test(): Unit = runTest {
		val client = ServerClient(
			client = HttpF2Client(
				httpClient = HttpClient(CIO) {
					install(ContentNegotiation) {
						json(Json {
							ignoreUnknownKeys = true
						})
					}
				},
				"http://localhost:1000",
			)
		)
		val file = PathMatchingResourcePatternResolver().getResource("classpath:application.yml").contentAsByteArray

		val command = ServerUploadCommand(
			command = ServerUploadCommandBody("blblbl"),
			file = F2FilePart(
				name = "zefile",
				content = file.inputStream().toByteReadChannel()
			)
		)
		val result = client.uploadFile().invoke(flow { emit(command) }).first()

		println(result)
		Assertions.assertThat(result.base64).isEqualTo(file.toBase64())

		val consumed = client.consume().invoke(listOf(
			ServerConsumeCommand("1"),
			ServerConsumeCommand("2"),
			ServerConsumeCommand("3")
		).asFlow()).toList()
		println(consumed)
		Assertions.assertThat(consumed).hasSize(3)
	}
}
