package de.holhar.kotlinreactivekafka.web

import de.holhar.kotlinreactivekafka.domain.CustomerRepository
import de.holhar.kotlinreactivekafka.domain.mapToCustomer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.json
import reactor.core.publisher.Mono

@Component
class CustomerHandler(private val repository: CustomerRepository) {

    // POI: Idiomatic Logging in Kotlin -> https://www.baeldung.com/kotlin/logging
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    fun saveCustomer(request: ServerRequest): Mono<ServerResponse> {
        return request.bodyToMono(CustomerDto::class.java)
                .map { it.mapToCustomer() }
                .flatMap { repository.save(it) }
                .flatMap { ServerResponse.ok().json().body(Mono.just(it.id!!), String::class.java) }
                .doOnError { logger.error("Could not process request", it) }
    }

    fun getCustomer(request: ServerRequest): Mono<ServerResponse> {
        return Mono.fromSupplier { request.pathVariable("customerId") }
                .flatMap { repository.findById(it) }
                .flatMap { ServerResponse.ok().json().body(Mono.just(it.toDto()), CustomerDto::class.java) }
                .doOnError { logger.error("Could not process request", it) }
    }
}
