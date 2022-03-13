import f2.client.ktor.http.HttpF2Client
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions
import kotlin.test.Test

class HttpF2ClientTest {

	@Test
	fun test(): Unit = runTest {
		val client = HttpF2Client(
			httpClient =  HttpClient(CIO) {
				install(JsonFeature)
			},
			"https://www.google.fr",
		)
		val result = client.getT("search?q=smartb").invoke().first()
		Assertions.assertThat(result).isNotNull
	}
}