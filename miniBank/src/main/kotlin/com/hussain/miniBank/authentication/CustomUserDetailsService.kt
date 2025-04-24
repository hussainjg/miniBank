package com.hussain.miniBank.authentication

import com.hussain.miniBank.Repo.UserRepo
import org.springframework.security.core.userdetails.*
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userRepo: UserRepo) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepo.findByUsername(username) ?: throw UsernameNotFoundException("User not found")

        return User.builder()
            .username(user.username)
            .password(user.password)
            .roles(user.role.toString())
            .build()
    }
}
