package com.system.cardealership.authuserservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AuthenticationServiceApplication

fun main(args: Array<String>) {
	runApplication<AuthenticationServiceApplication>(*args)
}