package dev.juseung.messengerlite.config

import dev.juseung.messengerlite.socket.ChatSocketHandler
import org.springframework.context.annotation.Configuration

import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry
import org.springframework.web.socket.WebSocketHandler

import org.springframework.context.annotation.Bean


@Configuration
@EnableWebSocket
class WebSocketConfig : WebSocketConfigurer {
    @Bean
    fun chatSocketHandler(): WebSocketHandler = ChatSocketHandler()

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(chatSocketHandler(), "/ws/chat").setAllowedOrigins("*")
    }
}
