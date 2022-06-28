package de.holhar.kotlinreactivekafka.web

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration
class CustomerRouteConfig(private val customerHandler: CustomerHandler) {

    val logger: Logger = LoggerFactory.getLogger(javaClass)

    /*
     * There is a lot of stuff happening here...
     * => POI:
     * - Lambda expressions and anonymous functions: https://kotlinlang.org/docs/lambdas.html#lambda-expressions-and-anonymous-functions
     * - Kotlin DSL -> https://www.javacodegeeks.com/2017/09/spring-webflux-kotlin-dsl-walkthrough-implementation.html
     * - Type-safe builders: https://kotlinlang.org/docs/type-safe-builders.html
     * - Function literals with receiver -> https://kotlinlang.org/docs/lambdas.html#function-literals-with-receiver
     */
    @Bean
    fun route(): RouterFunction<ServerResponse> = router {
        logger.info("route request")
        ("/customers").nest {
            accept(MediaType.APPLICATION_JSON).nest {
                POST("", customerHandler::saveCustomer)
            }

            GET("/{customerId}", customerHandler::getCustomer)
        }
    }
}
