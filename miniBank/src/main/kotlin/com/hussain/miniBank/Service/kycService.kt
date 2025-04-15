package com.hussain.miniBank.Service

import com.hussain.miniBank.Repo.*
import com.hussain.miniBank.model.*
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
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
        val user = usersRepository.findById(userId).orElseThrow {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found.")
        }
        if(dateOfBirth.isAfter(LocalDate.now())) {
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Date of Birth must not be in the future"
            )
        }
        val age = LocalDate.now().year - dateOfBirth.year
        if(age < 18){
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Age must be ABOVE 18.."
            )
        }

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