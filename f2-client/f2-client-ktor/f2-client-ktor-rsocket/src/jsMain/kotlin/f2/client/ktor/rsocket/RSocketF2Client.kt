package f2.client.ktor.rsocket

import f2.client.F2Client
import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.promise
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.js.Promise

@JsExport
@JsName("RSocketF2Client")
actual class RSocketF2Client(
	private val rSocketClient: RSocketClient,
) : F2Client {


	actual override fun supplier(route: String) = object : F2Supplier<String> {
		override fun invoke() = GlobalScope.promise {
			rSocketClient.requestStream(route).map {
				handlePayloadResponse(it)
			}.toList().toTypedArray()
		}
	}

	actual override fun function(route: String): F2Function<String, String> = object : F2Function<String, String> {
		override fun invoke(cmd: String) = GlobalScope.promise {
			val payload = rSocketClient.requestResponse(route, cmd)
			handlePayloadResponse(payload)
		}
	}

	actual override fun consumer(route: String): F2Consumer<String> = object : F2Consumer<String> {
		override fun invoke(cmd: String): Promise<Unit> = GlobalScope.promise {
			rSocketClient.fireAndForget(route, cmd)
		}
	}

	private val json = Json {
		ignoreUnknownKeys = true
	}

	private fun handlePayloadResponse(payload: String): String {
		return json.decodeFromString<Map<String, String>>(payload).get("payload") ?: ""
	}
}
