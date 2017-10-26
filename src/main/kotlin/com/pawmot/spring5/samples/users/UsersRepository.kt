package com.pawmot.spring5.samples.users

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface UsersRepository : ReactiveMongoRepository<User, ObjectId> {

    fun findByLogin(login: String): Mono<User>
}