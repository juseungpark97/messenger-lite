package dev.juseung.messengerlite.socket

import dev.juseung.messengerlite.config.jwt.JwtTokenProvider
import dev.juseung.messengerlite.domain.repository.ChannelUserRepository
import org.springframework.web.socket.*
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.util.concurrent.ConcurrentHashMap

class ChatSocketHandler(
    private val jwtTokenProvider: JwtTokenProvider,
    private val channelUserRepository: ChannelUserRepository
) : TextWebSocketHandler() {

    private val roomMap = ConcurrentHashMap<String, MutableSet<WebSocketSession>>()

    override fun afterConnectionEstablished(session: WebSocketSession) {
        val token = session.uri?.query?.substringAfter("token=") ?: return
        val userId = jwtTokenProvider.getUserIdFromToken(token)  // userId: Long

        val channelUsers = channelUserRepository.findAllByUserId(userId)

        channelUsers.forEach { cu ->
            val channelId = cu.channel.id!!.toString()
            roomMap.computeIfAbsent(channelId) { mutableSetOf() }.add(session)
            println("[$userId] joined $channelId")
        }
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val payload = message.payload
        val channelId = extractChannelIdFromPayload(payload)

        roomMap[channelId]?.forEach {
            if (it.isOpen) it.sendMessage(TextMessage(payload))
        }
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        roomMap.values.forEach { it.remove(session) }
    }

    private fun extractChannelIdFromPayload(payload: String): String {
        return Regex("\"channelId\"\\s*:\\s*\"([^\"]+)\"")
            .find(payload)?.groups?.get(1)?.value ?: "unknown"
    }
}