package com.hussain.miniBank

import com.hussain.miniBank.Repo.*
import com.hussain.miniBank.model.UserEntity
import com.hussain.miniBank.model.Role
import org.springframework.boot.runApplication
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.password.PasswordEncoder

class InitUserRunner {
    @Bean
    fun initUsers(userRepoo: UserRepo, passwordEncoder: PasswordEncoder) = CommandLineRunner {
        val user = UserEntity(
            username = "testuser",
            password = passwordEncoder.encode("password123"),
            role = Role.USER
        )
        if (userRepoo.findByUsername(user.username) == null) {
            println("Creating user ${user.username}")
            userRepoo.save(user)
        } else  {
            println("User ${user.username} already exists")
        }
    }
}

fun main(args: Array<String>) {
    runApplication<InitUserRunner>(*args)
}