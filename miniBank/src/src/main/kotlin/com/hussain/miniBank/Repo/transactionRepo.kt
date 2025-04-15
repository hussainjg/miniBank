package com.hussain.miniBank.Repo

import com.hussain.miniBank.model.TransactionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface TransactionRepo : JpaRepository<TransactionEntity, Long>

