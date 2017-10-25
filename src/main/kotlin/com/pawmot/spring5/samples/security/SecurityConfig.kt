package com.pawmot.spring5.samples.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.HttpSecurity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.MapUserDetailsRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebFluxSecurity
class SecurityConfig {

    @Bean
    fun userDetails(): UserDetailsRepository {
        val user = User("pawmot", passwordEncoder().encode("pass"), listOf(SimpleGrantedAuthority("ROLE_USER")))
        return MapUserDetailsRepository(user)
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun securityFilter(httpSecurity: HttpSecurity) = httpSecurity
            .authorizeExchange()
            .pathMatchers("/application/**").permitAll()
            .pathMatchers("/api/**").hasRole("USER")
            .and()
            .formLogin().disable()
            .logout().disable()
            .build()
}