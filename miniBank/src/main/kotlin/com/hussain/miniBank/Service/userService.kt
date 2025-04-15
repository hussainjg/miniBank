package com.hussain.miniBank.Service

import com.hussain.miniBank.Repo.UserRepo
import com.hussain.miniBank.model.*
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UsersServices(private val usersRepository: UserRepo) {

    fun registerUser(username: String, password: String): UserEntity {

        val specialRegex = Regex("[^a-zA-Z0-9]")
        val upperRegex = Regex("[A-Z]")

        if (usersRepository.findByUsername(username) != null) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists")
        }

        if (!specialRegex.containsMatchIn(password) || !upperRegex.containsMatchIn(password)) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Password must contain at least one special character and upper case letter."
            )
        }

        val newUser = UserEntity(username = username, password = password)
        return usersRepository.save(newUser)
    }

    fun getAllUsers(): List<UserEntity> = usersRepository.findAll()
}