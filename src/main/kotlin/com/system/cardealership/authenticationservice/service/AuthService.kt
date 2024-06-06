package com.system.cardealership.authenticationservice.service

import com.system.cardealership.authenticationservice.entity.User
import com.system.cardealership.authenticationservice.repository.UserRepository
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val jwtService: JwtService
) {

    fun loginUser(username: String, password: String): String {
        // Find user by username
        val user = userRepository.findByUserName(username)
            ?: throw UsernameNotFoundException("User not found with username: $username")

        // Check if user exists and password matches
        if (user.password == password) {
            return jwtService.generateToken(user)
        } else {
            throw UsernameNotFoundException("Provided incorrect credentials")
        }
    }
}
