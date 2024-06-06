package com.system.cardealership.authenticationservice.repository
import com.system.cardealership.authenticationservice.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface UserRepository : JpaRepository<User?, Long?> {
    fun findByUserName(userName: String?): User?
}