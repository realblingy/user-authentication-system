package com.system.cardealership.authenticationservice.controller

import com.system.cardealership.authenticationservice.dto.UserLogin
import com.system.cardealership.authenticationservice.service.AuthService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/auth")
class AuthController(private val authService: AuthService) {

    @PostMapping("/login")
    fun loginUser(@RequestBody userLogin: UserLogin): ResponseEntity<String?> {
        val (username, password) = userLogin
        try {
            val token = authService.loginUser(username, password)
            return ResponseEntity.ok(token)

        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.message)
        }
    }


}