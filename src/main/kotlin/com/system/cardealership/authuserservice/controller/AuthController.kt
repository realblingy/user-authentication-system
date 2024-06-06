package com.system.cardealership.authuserservice.controller

import com.system.cardealership.authuserservice.dto.RegisterUser
import com.system.cardealership.authuserservice.dto.UserLogin
import com.system.cardealership.authuserservice.service.AuthService
import com.system.cardealership.authuserservice.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService,
    private val userService: UserService
) {

    @PostMapping("/login")
    fun loginUser(@RequestBody userLogin: UserLogin): ResponseEntity<String?> {
        val (username, password) = userLogin
        try {
            val token = authService.loginUser(username, password)
            return ResponseEntity.ok(token)

        } catch (e: UsernameNotFoundException) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.message)
        }
    }

    @PostMapping("/register")
    fun registerUser(@RequestBody registerUser: RegisterUser): ResponseEntity<String?> {
        val (username, password) = registerUser
        val token = userService.register(username, password)
        return ResponseEntity.ok(token)
    }

}