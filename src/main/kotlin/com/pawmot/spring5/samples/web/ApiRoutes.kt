package com.pawmot.spring5.samples.web

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.router

@Configuration
class ApiRoutes(val timeHandler: TimeHandler, val proxyHandler: ProxyHandler) {

    @Bean
    fun apiRouter() = router {
        ("/api").nest {
            ("/time").nest {
                GET("/", timeHandler::get)
            }
            ("/timeSse" and accept(MediaType.TEXT_EVENT_STREAM)).nest {
                GET("/", timeHandler::sse)
            }
            ("/timeProxy").nest {
                GET("/", proxyHandler::proxy)
            }
        }
    }
}