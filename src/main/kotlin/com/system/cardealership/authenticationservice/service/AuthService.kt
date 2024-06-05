package com.system.cardealership.authenticationservice.service

import com.system.cardealership.authenticationservice.entity.User
import com.system.cardealership.authenticationservice.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthService {
    @Autowired
    lateinit var userRepository: UserRepository

    fun loginUser(username: String, password: String): User? {
        // Find user by username
        val user = userRepository.findByUsername(username)

        // Check if user exists and password matches
        if (user != null && user.password == password) {
            return user
        }
        // Return null if user not found or password does not match
        return null
    }
}
