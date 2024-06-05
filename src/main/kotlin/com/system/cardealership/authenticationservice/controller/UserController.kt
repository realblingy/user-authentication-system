package com.system.cardealership.authenticationservice.controller

import com.system.cardealership.authenticationservice.entity.User
import com.system.cardealership.authenticationservice.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/user")
class UserController {
    @Autowired
    lateinit var userRepository: UserRepository

    @GetMapping("/")
    fun getUserByUsername(@RequestParam username: String): ResponseEntity<User?> {
        val user = userRepository.findByUsername(username)
        return ResponseEntity.ok(user)
    }
}