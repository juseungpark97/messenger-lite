package dev.juseung.messengerlite.domain.repository

import dev.juseung.messengerlite.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
}