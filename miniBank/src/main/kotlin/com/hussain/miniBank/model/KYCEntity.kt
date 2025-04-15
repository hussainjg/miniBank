package com.hussain.miniBank.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDate

@Entity
@Table(name = "kyc")
data class KYCEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val firstName: String = "",
    val lastName: String = "",
    val dateOfBirth: LocalDate = LocalDate.now(),
    val salary: BigDecimal = BigDecimal.ZERO,

    @OneToOne
    @JoinColumn(name = "user_id")
    val user: UserEntity? = null
) {
    constructor() : this(0, "", "", LocalDate.now(), BigDecimal.ZERO, null)
}