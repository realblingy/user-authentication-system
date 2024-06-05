package com.system.cardealership.authenticationservice.config

import com.system.cardealership.authenticationservice.service.JwtService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter: OncePerRequestFilter() {

    @Autowired
    lateinit var jwtService: JwtService

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        // Check if valid request
        val authHeader = request.getHeader("Authorization")
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return
        }
        val token = authHeader.substring(7)
        val username = jwtService.extractUsername(token)


    }

}