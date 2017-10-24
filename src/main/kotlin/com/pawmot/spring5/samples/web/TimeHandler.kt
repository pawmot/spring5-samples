package com.pawmot.spring5.samples.web

import com.pawmot.spring5.samples.services.Clock
import io.reactivex.BackpressureStrategy
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyToServerSentEvents
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class TimeHandler(private val clock: Clock) {

    fun get(request: ServerRequest) : Mono<ServerResponse> {
        return ok().body(clock.time.firstOrError().toFlowable(), String::class.java)
    }

    fun sse(request: ServerRequest) : Mono<ServerResponse> {
        return ok().bodyToServerSentEvents(Flux.from(clock.time.toFlowable(BackpressureStrategy.LATEST)))
    }
}