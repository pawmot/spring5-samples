package com.pawmot.spring5.samples

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.function.client.WebClient

@SpringBootApplication
@EnableWebFlux
class App {

    @Bean
    fun webClient(): WebClient {
        return WebClient.create()
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(App::class.java, *args)
}