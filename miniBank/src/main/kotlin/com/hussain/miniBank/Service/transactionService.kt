package com.hussain.miniBank.Service

import com.hussain.miniBank.Repo.*
import com.hussain.miniBank.model.*
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class TransactionsServices(
    private val transactionRepo: TransactionRepo,
    private val accountRepo: AccountRepo
) {
    fun transfer(fromAccountId: Long, toAccountId: Long, amount: BigDecimal): TransactionEntity {
        val from = accountRepo.findById(fromAccountId)
            .orElseThrow { Exception("Source account not found") }

        val to = accountRepo.findById(toAccountId)
            .orElseThrow { Exception("Destination account not found") }

        if (!from.isActive || !to.isActive) {
            throw Exception("One of the accounts is inactive.")
        }

        if (from.balance < amount) {
            throw Exception("Insufficient balance in source account.")
        }

        val updatedFrom = from.copy(balance = from.balance - amount)
        val updatedTo = to.copy(balance = to.balance + amount)

        accountRepo.saveAll(listOf(updatedFrom, updatedTo))

        val transaction = TransactionEntity(
            fromAccount = updatedFrom,
            toAccount = updatedTo,
            amount = amount
        )

        return transactionRepo.save(transaction)
    }
}