package com.hussain.miniBank.Controller

import com.hussain.miniBank.Service.*
import com.hussain.miniBank.model.UserEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


data class RegisterRequest(val username: String, val password: String)

@RestController
@RequestMapping("/api/user")
class UsersController(private val usersServices: UsersServices) {

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): ResponseEntity<UserEntity> {
        val user = usersServices.registerUser(request.username, request.password)
        return ResponseEntity.ok(user)
    }

    @GetMapping
    fun getAll(): List<UserEntity> = usersServices.getAllUsers()
}