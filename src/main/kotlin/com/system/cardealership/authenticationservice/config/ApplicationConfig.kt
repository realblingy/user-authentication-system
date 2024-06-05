package com.system.cardealership.authenticationservice.config

import com.system.cardealership.authenticationservice.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class UserDetailsServiceImpl : UserDetailsService {
    @Autowired
    lateinit var userRepository: UserRepository

    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findByUsername(username) ?: throw UsernameNotFoundException(username)
    }
}

@Configuration
class ApplicationConfig {

    @Bean
    fun userDetailsService(userRepository: UserRepository): UserDetailsServiceImpl {
        return UserDetailsServiceImpl()
    }
}