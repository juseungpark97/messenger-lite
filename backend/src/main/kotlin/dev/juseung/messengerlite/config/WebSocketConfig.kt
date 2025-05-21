package dev.juseung.messengerlite.config

import dev.juseung.messengerlite.config.jwt.JwtTokenProvider
import dev.juseung.messengerlite.domain.repository.ChannelUserRepository
import dev.juseung.messengerlite.socket.ChatSocketHandler
import org.springframework.context.annotation.Configuration

import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry
import org.springframework.web.socket.WebSocketHandler

import org.springframework.context.annotation.Bean


@Configuration
@EnableWebSocket
class WebSocketConfig(
    private val jwtTokenProvider: JwtTokenProvider,
    private val channelUserRepository: ChannelUserRepository
    ) : WebSocketConfigurer {
    @Bean
    fun chatSocketHandler(): WebSocketHandler = ChatSocketHandler(jwtTokenProvider, channelUserRepository)

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(chatSocketHandler(), "/ws/chat").setAllowedOrigins("*")
    }
}
