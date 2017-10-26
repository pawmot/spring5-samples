package com.pawmot.spring5.samples.users

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.crypto.password.PasswordEncoder

@Document(collection = "users")
data class User(@Id val id: ObjectId, @Indexed(unique = true) val login: String, val passwordHash: String, val roles: Set<Role>) {
    companion object {
        fun from(dto: RegisterDto, passwordEncoder: PasswordEncoder) =
                User(ObjectId(), dto.login, passwordEncoder.encode(dto.password), setOf(Role.USER))
    }
}