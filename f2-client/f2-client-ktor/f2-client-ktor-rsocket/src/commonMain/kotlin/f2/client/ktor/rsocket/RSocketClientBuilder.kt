package f2.client.ktor.rsocket

import io.ktor.client.*
import io.ktor.client.features.websocket.*
import io.rsocket.kotlin.core.RSocketConnector
import io.rsocket.kotlin.payload.PayloadMimeType
import io.rsocket.kotlin.transport.ktor.client.RSocketSupport
import kotlin.time.ExperimentalTime

expect fun rsocketClientBuilder(): RSocketClientBuilder

expect class RSocketClientBuilder {
	fun build(): HttpClient
}