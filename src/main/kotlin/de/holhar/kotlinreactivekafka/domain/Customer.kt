package de.holhar.kotlinreactivekafka.domain

import de.holhar.kotlinreactivekafka.web.CustomerDto
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("customers")
data class Customer(
        @Id
        var id: String?,
        val firstName: String,
        val lastName: String,
        val email: String
)

fun CustomerDto.mapToCustomer(): Customer = Customer(
        id = null,
        firstName = firstName,
        lastName = lastName,
        email = lastName
)
