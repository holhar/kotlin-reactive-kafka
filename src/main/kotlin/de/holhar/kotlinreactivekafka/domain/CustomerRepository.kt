package de.holhar.kotlinreactivekafka.domain

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository: ReactiveCrudRepository<Customer, String>
