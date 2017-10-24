package com.pawmot.spring5.samples.web

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono

@Service
class ProxyHandler(private val client: WebClient) {

    fun proxy(request: ServerRequest): Mono<ServerResponse> {
        return client.get()
                .uri("http://localhost:8080/api/time")
                .exchange()
                .flatMap { it.bodyToMono(String::class.java) }
                .flatMap { ok().syncBody(it) }
    }
}