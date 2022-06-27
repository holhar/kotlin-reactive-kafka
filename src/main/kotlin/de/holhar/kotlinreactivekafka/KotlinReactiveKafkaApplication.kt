package de.holhar.kotlinreactivekafka

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinReactiveKafkaApplication

fun main(args: Array<String>) {
    runApplication<KotlinReactiveKafkaApplication>(*args)
}
