package com.pawmot.spring5.samples.users

enum class Role {
    USER;

    fun toSpringRole() : String = "ROLE_" + this.name
}