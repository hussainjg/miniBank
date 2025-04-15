package com.hussain.miniBank.Service

import com.hussain.miniBank.Repo.*
import com.hussain.miniBank.model.*
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate

@Service
class KYCServices(
    private val kycRepo: KycRepo,
    private val usersRepository: UserRepo) {
    fun createProfile(
        userId: Long,
        firstName: String,
        lastName: String,
        dateOfBirth: LocalDate,
        salary: BigDecimal): KYCEntity {
        val user = usersRepository.findById(userId).orElseThrow { Exception("User not found") }

        val profile = KYCEntity(
            firstName = firstName,
            lastName = lastName,
            dateOfBirth = dateOfBirth,
            salary = salary,
            user = user
        )
        return kycRepo.save(profile)
    }

    fun getAllProfiles(): List<KYCEntity> = kycRepo.findAll()
}