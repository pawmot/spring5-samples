package com.pawmot.spring5.samples.security

import com.pawmot.spring5.samples.users.UsersRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class SecurityRepository(private val usersRepository: UsersRepository) : UserDetailsRepository {
    override fun findByUsername(login: String): Mono<UserDetails> =
            usersRepository.findByLogin(login)
            .map(Companion::toUserDetails)

    companion object {
        private fun toUserDetails(user: com.pawmot.spring5.samples.users.User): UserDetails {
            return User(user.login, user.passwordHash, user.roles.map {
                SimpleGrantedAuthority(it.toSpringRole())
            })
        }
    }
}