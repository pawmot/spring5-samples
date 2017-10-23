package com.pawmot.spring5.samples.web

import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import org.springframework.stereotype.Service
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
// Use the following lines instead of `share()` to keep the hot observable going even in absence of any subscriptions.
// Now it is being disconnected when last subscriber goes away.
//            .replay(1)
//            .autoConnect()

    fun get(request: ServerRequest) : Mono<ServerResponse> {
        return ok().body(time.firstOrError().toFlowable(), String::class.java)
    }

    fun sse(request: ServerRequest) : Mono<ServerResponse> {
        return ok().bodyToServerSentEvents(Flux.from(time.toFlowable(BackpressureStrategy.LATEST)))
    }
}