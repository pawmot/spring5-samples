package com.pawmot.spring5.samples.services

import io.reactivex.Observable
import org.springframework.stereotype.Service
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

@Service
class Clock {

    val time: Observable<String> = Observable.interval(1, TimeUnit.SECONDS)
            .map { _ -> LocalTime.now() }
            .map { it.format(DateTimeFormatter.ofPattern("HH:mm:ss")) }
            .share()
// Use the following lines instead of `share()` to keep the hot observable going even in absence of any subscriptions.
// Now it is being disconnected when last subscriber goes away.
//            .replay(1)
//            .autoConnect()
}