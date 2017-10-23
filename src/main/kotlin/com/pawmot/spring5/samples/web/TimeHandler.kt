package com.pawmot.spring5.samples.web

import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyToServerSentEvents
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

@Service
class TimeHandler {
    private val time = Observable.interval(1, TimeUnit.SECONDS)
            .map { _ -> LocalTime.now() }
            .map { it.format(DateTimeFormatter.ofPattern("HH:mm:ss")) }
            .share()

    fun get(request: ServerRequest) : Mono<ServerResponse> {
        return ok().body(fromObject(LocalTime.now().toString()))
    }

    fun sse(request: ServerRequest) : Mono<ServerResponse> {
        return ok().bodyToServerSentEvents(Flux.from(time.toFlowable(BackpressureStrategy.LATEST)))
    }
}