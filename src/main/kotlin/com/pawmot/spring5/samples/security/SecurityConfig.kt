package com.pawmot.spring5.samples.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebFluxSecurity
class SecurityConfig {

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun securityFilter(httpSecurity: HttpSecurity) = httpSecurity
            .authorizeExchange()
            .pathMatchers("/application/**").permitAll()
            .pathMatchers(HttpMethod.POST, "/api/users").permitAll()
            .pathMatchers("/api/**").hasRole("USER")
            .and()
            .formLogin().disable()
            .logout().disable()
            .build()
}