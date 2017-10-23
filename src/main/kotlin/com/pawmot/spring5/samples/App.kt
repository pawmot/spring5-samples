package com.pawmot.spring5.samples

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.reactive.config.EnableWebFlux

@SpringBootApplication
@EnableWebFlux
class App

fun main(args: Array<String>) {
    SpringApplication.run(App::class.java, *args)
}