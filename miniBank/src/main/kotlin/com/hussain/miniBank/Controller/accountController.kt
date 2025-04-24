package com.hussain.miniBank.Controller

import com.hussain.miniBank.Service.*
import com.hussain.miniBank.model.AccountEntity
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal


@RestController
@RequestMapping("/api/accounts")
class AccountController(private val accountService: AccountService) {


    @PostMapping("/v1/accounts")
    fun createAccount(@RequestBody request: CreateAccountRequest): AccountEntity =
        accountService.createAccount(
            userId = request.userId,
            balance = request.initialBalance
        )

    @DeleteMapping("/close/{accountId}")
    fun closeAccount(@PathVariable accountId: Long) =
        accountService.closeAccount(accountId)

    @GetMapping("/user/{userId}")
    fun getUserAccounts(@PathVariable userId: Long): List<AccountEntity> =
        accountService.getUserAccounts(userId)

    @GetMapping("/v1/accounts")
    fun getAllAccounts(): Map<String, List<AccountEntity>> {
        val allAccounts = accountService.getAllAccounts()
        return mapOf("accounts" to allAccounts)
    }
}
data class CreateAccountRequest(
    val userId: Long,
    val initialBalance: BigDecimal
)