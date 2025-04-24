package com.hussain.miniBank.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(unique = true)
    val username: String = "",
    val password: String = "",

    @Enumerated(EnumType.STRING)
    val role: Role = Role.USER
) {
    constructor() : this(0,"","", Role.USER)
}


enum class Role {
    USER,
    ADMIN
}