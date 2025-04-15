package com.hussain.miniBank.Service

import com.hussain.miniBank.Repo.*
import com.hussain.miniBank.model.AccountEntity
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class AccountService(
    private val accountRepo: AccountRepo,
    private val usersRepository: UserRepo
) {
    fun createAccount(userId: Long, balance: BigDecimal): AccountEntity {
        val user = usersRepository.findById(userId).orElseThrow { Exception("User not found") }
        val newAccount = AccountEntity(
            user = user,
            balance = balance)
        return accountRepo.save(newAccount)
    }

    fun closeAccount(accountId: Long) {
        val acc = accountRepo.findById(accountId).orElseThrow { Exception("Account not found") }
        val updated = acc.copy(isActive = false)
        accountRepo.save(updated)
    }

    fun getAllAccounts(): List<AccountEntity> = accountRepo.findAll()

    fun getUserAccounts(userId: Long): List<AccountEntity> =
        accountRepo.findByUserId(userId)
}