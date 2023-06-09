package f2.feature.cloudEvent.storming

import f2.dsl.event.CloudEvent
import f2.feature.cloudEvent.storming.entity.CloudEventEntityRepository
import java.util.function.Supplier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.core.publisher.Flux

@Configuration
class StormingCloudEventFunction {

	@Bean
	fun cloudEvents(repo: CloudEventEntityRepository): Supplier<Flux<CloudEvent<*>>> {
		return Supplier {
			repo.findAll().map { it.event }
		}
	}
}
