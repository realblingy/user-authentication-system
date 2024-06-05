package com.system.cardealership.authenticationservice.controller

import com.system.cardealership.authenticationservice.dto.UserLogin
import com.system.cardealership.authenticationservice.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/auth")
class AuthController {
    @Autowired
    private lateinit var authService: AuthService

    @PostMapping("/login")
    fun loginUser(@RequestBody userLogin: UserLogin): ResponseEntity<String?> {
        val (username, password) = userLogin
        val user = authService.loginUser(username, password)
        if (user != null) {
            return ResponseEntity.ok("Success")
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid password")
    }


}