package com.pawmot.spring5.samples.users

import org.springframework.dao.DuplicateKeyException
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.badRequest
import org.springframework.web.reactive.function.server.ServerResponse.status
import reactor.core.publisher.Mono

@Service
class UsersHandler(private val usersRepository: UsersRepository, private val passwordEncoder: PasswordEncoder) {

    fun register(req: ServerRequest): Mono<ServerResponse> {
        return req.bodyToMono(RegisterDto::class.java)
                .map { User.from(it, passwordEncoder) }
                .flatMap { usersRepository.insert(it) }
                .flatMap { status(HttpStatus.CREATED).build() }
                .onErrorResume(DuplicateKeyException::class.java) {
                    badRequest().syncBody("User with such login already exists!")
                }
    }
}