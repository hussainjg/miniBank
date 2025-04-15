package com.hussain.miniBank.Repo

import com.hussain.miniBank.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepo : JpaRepository<UserEntity, Long>{
    fun findByUsername(username: String): UserEntity?
}
