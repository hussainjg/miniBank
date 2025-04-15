package com.hussain.miniBank.Service

import com.hussain.miniBank.Repo.UserRepo
import com.hussain.miniBank.model.*
import org.springframework.stereotype.Service

@Service
class UsersServices(private val usersRepository: UserRepo) {

    fun registerUser(username: String, password: String): UserEntity {
        if (usersRepository.findByUsername(username) != null) {
            throw Exception("Username already exists")
        }

        val newUser = UserEntity(username = username, password = password)
        return usersRepository.save(newUser)
    }

    fun getAllUsers(): List<UserEntity> = usersRepository.findAll()
}