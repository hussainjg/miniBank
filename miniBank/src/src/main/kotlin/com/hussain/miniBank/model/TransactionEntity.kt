package com.hussain.miniBank.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "transactions")
data class TransactionEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val amount: BigDecimal,

    @ManyToOne
    @JoinColumn(name = "source_account")
    val fromAccount: AccountEntity? = null,

    @ManyToOne
    @JoinColumn(name = "destination_account")
    val toAccount: AccountEntity? = null
)