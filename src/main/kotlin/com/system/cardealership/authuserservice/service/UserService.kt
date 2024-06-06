package com.system.cardealership.authuserservice.service

import com.system.cardealership.authuserservice.entity.User
import com.system.cardealership.authuserservice.enums.Role
import com.system.cardealership.authuserservice.repository.UserRepository
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val authService: AuthService
) {

    fun findUserByUsername(username: String): User {
        return userRepository.findByUserName(username) ?: throw UsernameNotFoundException("User $username not found")
    }

    fun register(username: String, password: String): String {
        // Check if the user already exists
        if (userRepository.findByUserName(username) != null) {
            throw IllegalArgumentException("User with username $username already exists")
        }

        // Save the new user
        userRepository.save(User(0L, userName = username, passWord = password, role = Role.USER))

        // Log in the user and return the token
        return authService.loginUser(username, password)
    }
}