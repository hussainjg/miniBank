package com.hussain.miniBank.model


import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.math.BigDecimal
import java.util.UUID

@Entity
@Table(name = "account")
data class AccountEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val balance: BigDecimal = BigDecimal.ZERO,

    val isActive: Boolean = true,

    val accountNumber: String = UUID.randomUUID().toString(),

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: UserEntity? = null
)