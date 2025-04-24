package com.hussain.miniBank.Service

import com.hussain.miniBank.Repo.UserRepo
import com.hussain.miniBank.model.*
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UsersServices(private val usersRepository: UserRepo) {

    fun registerUser(username: String, password: String, role: Role): UserEntity {

        val specialRegex = Regex("[^a-zA-Z0-9]")
        val upperRegex = Regex("[A-Z]")
        val numberRegex = Regex("[0-9]")
        val errors = mutableListOf<ResponseStatusException>()
        if (usersRepository.findByUsername(username) != null) {
            errors.add(ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists"))
        }
        if (username.length <= 8) {
            errors.add(ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Username must be more than 8 characters.\n"
            ))
        }
        if (password.length <= 8) {
            errors.add(ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Password must be more than 8 characters.\n"
            ))
        }
        if (specialRegex.containsMatchIn(username) || numberRegex.containsMatchIn(username)) {
            errors.add(ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Username must not contain special characters or numbers.\n"
            ))
        }
        if (!specialRegex.containsMatchIn(password) || !upperRegex.containsMatchIn(password) || !numberRegex.containsMatchIn(
                password
            )
        ) {
            errors.add(ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Password must contain at least one special character and upper case letter and numbers.\n"
            ))
        }


        if (errors.isNotEmpty()) throw UserRegistrationException(errors)
        val newUser = UserEntity(username = username, password = password, role = role)
        return usersRepository.save(newUser)
    }

    fun getAllUsers(): List<UserEntity> = usersRepository.findAll()
}

class UserRegistrationException(
    private val errors: List<ResponseStatusException>,
): ResponseStatusException(HttpStatus.BAD_REQUEST, errors.joinToString("\n"))