package com.hussain.miniBank.Controller

import com.hussain.miniBank.Repo.UserRepo
import com.hussain.miniBank.Service.UsersServices
import com.hussain.miniBank.model.Role
import com.hussain.miniBank.model.UserEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*

data class RegisterRequest(

    val username: String,
    val password: String,
    val role: Role = Role.USER)

@RestController
@RequestMapping("/api/users")
class UsersController(
    private val usersServices: UsersServices,
    private val userRepository: UserRepo,
    private val passwordEncoder: PasswordEncoder
) {

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): UserEntity {
        val user = usersServices.registerUser(request.username, request.password, request.role)
        val hashedPassword = passwordEncoder.encode(user.password)
        val newUser = user.copy(password = hashedPassword)
        return userRepository.save(newUser)
    }

    @GetMapping("/getAll")
    fun getAll(): List<UserEntity> = usersServices.getAllUsers()
}
