package com.hussain.miniBank.Service

import com.hussain.miniBank.Repo.*
import com.hussain.miniBank.model.AccountEntity
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.math.BigDecimal

@Service
class AccountService(
    private val accountRepo: AccountRepo,
    private val usersRepository: UserRepo
) {
    fun createAccount(userId: Long, balance: BigDecimal?): AccountEntity {
        val user = usersRepository.findById(userId).orElseThrow {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found.")
        }

        val userAccountsCount = accountRepo.findByUserId(userId).size
        
        if (userAccountsCount >= 5) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Maximum accounts reached for this user.")
        }
        if (balance == null) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "please enter balance to create a new account.")
        }
        if (balance < BigDecimal("0")) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Balance must be in positive.")
        }
        val newAccount = AccountEntity(user = user, balance = balance)
        return accountRepo.save(newAccount)

    }

    fun closeAccount(accountId: Long) {
        val acc = accountRepo.findById(accountId).orElseThrow {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found.")
        }
        val updated = acc.copy(isActive = false)
        accountRepo.save(updated)
    }

    fun getAllAccounts(): List<AccountEntity> = accountRepo.findAll()

    fun getUserAccounts(userId: Long): List<AccountEntity> =
        accountRepo.findByUserId(userId)
}