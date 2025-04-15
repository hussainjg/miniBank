package com.hussain.miniBank.Repo



import com.hussain.miniBank.model.AccountEntity

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepo : JpaRepository<AccountEntity, Long>{
    fun findByUserId(userId: Long): List<AccountEntity>
}

