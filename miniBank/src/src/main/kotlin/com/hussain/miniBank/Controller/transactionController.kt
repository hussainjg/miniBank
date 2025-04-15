package com.hussain.miniBank.Controller

import com.hussain.miniBank.Service.*
import com.hussain.miniBank.model.TransactionEntity
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal

data class TransferRequest(val fromAccountId: Long, val toAccountId: Long, val amount: BigDecimal)

@RestController
@RequestMapping("/api/transfer")
class TransactionController(private val transactionsServices: TransactionsServices) {

    @PostMapping
    fun transfer(@RequestBody req: TransferRequest): TransactionEntity {
        return transactionsServices.transfer(req.fromAccountId, req.toAccountId, req.amount)
    }
}