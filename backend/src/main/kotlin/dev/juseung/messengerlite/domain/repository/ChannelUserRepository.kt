package dev.juseung.messengerlite.domain.repository

import dev.juseung.messengerlite.domain.ChannelUser
import org.springframework.data.jpa.repository.JpaRepository

interface ChannelUserRepository : JpaRepository<ChannelUser, Long> {
    fun findAllByUserId(userId: Long): List<ChannelUser>
}