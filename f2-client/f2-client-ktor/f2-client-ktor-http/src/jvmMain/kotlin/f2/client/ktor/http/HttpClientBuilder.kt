package f2.client.ktor.http

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import f2.client.F2Client
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.json.JacksonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer

actual class HttpClientBuilder {
	private fun httpClient(): HttpClient {
		return HttpClient(CIO) {
			install(JsonFeature) {
				serializer = KotlinxSerializer()
//				serializer = JacksonSerializer {
//					this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
//						.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
//						.registerModule(KotlinModule())
//						.setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE)
//				}
			}
		}
	}

	fun build(
		urlBase: String
	): F2Client {
		val httpCLient = httpClient()
		return HttpF2Client(
			httpClient = httpCLient,
			urlBase
		)
	}
}

actual fun httpClientBuilder() = HttpClientBuilder()
