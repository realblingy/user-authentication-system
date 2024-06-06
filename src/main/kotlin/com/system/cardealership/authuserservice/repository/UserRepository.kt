package com.system.cardealership.authuserservice.repository
import com.system.cardealership.authuserservice.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface UserRepository : JpaRepository<User?, Long?> {
    fun findByUserName(userName: String?): User?
}