package de.holhar.kotlinreactivekafka.web

import de.holhar.kotlinreactivekafka.domain.Customer

data class CustomerDto(val firstName: String, val lastName: String, val email: String)

fun Customer.toDto() = CustomerDto(firstName, lastName, email)
