package com.system.cardealership.authenticationservice.controller

import com.system.cardealership.authenticationservice.entity.User
import com.system.cardealership.authenticationservice.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/user")
class UserController(private val userRepository: UserRepository) {

    @GetMapping("/{username}")
    fun getUserByUsername(@PathVariable username: String): ResponseEntity<User?> {
        val user = userRepository.findByUserName(username)
        return ResponseEntity.ok(user)
    }
}