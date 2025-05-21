package dev.juseung.messengerlite.domain.repository

import dev.juseung.messengerlite.domain.Channel
import org.springframework.data.jpa.repository.JpaRepository

interface ChannelRepository : JpaRepository<Channel, Long>