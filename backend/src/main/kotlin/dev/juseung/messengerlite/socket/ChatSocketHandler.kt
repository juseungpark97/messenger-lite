package dev.juseung.messengerlite.socket

import org.springframework.web.socket.*
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.util.concurrent.ConcurrentHashMap

class ChatSocketHandler : TextWebSocketHandler() {

    private val roomMap = ConcurrentHashMap<String, MutableSet<WebSocketSession>>()

    override fun afterConnectionEstablished(session: WebSocketSession) {
        val userId = extractUserIdFromQuery(session)
        val dummyChannels = listOf("channel-1", "channel-2")

        dummyChannels.forEach { channelId ->
            roomMap.computeIfAbsent(channelId) { mutableSetOf() }.add(session)
            println("[$userId] joined $channelId")
        }
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val payload = message.payload
        val channelId = extractChannelIdFromPayload(payload)  // 예시: JSON 파싱해서 추출

        roomMap[channelId]?.forEach {
            if (it.isOpen) it.sendMessage(TextMessage(payload))
        }
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        roomMap.values.forEach { it.remove(session) }
    }

    private fun extractUserIdFromQuery(session: WebSocketSession): String {
        return session.uri?.query?.substringAfter("token=")?.takeIf { it.isNotBlank() } ?: "anonymous"
    }

    private fun extractChannelIdFromPayload(payload: String): String {
        // 간단한 문자열 파싱 or JSON 파싱 예시
        return Regex("\"channelId\"\\s*:\\s*\"([^\"]+)\"")
            .find(payload)?.groups?.get(1)?.value ?: "channel-1"
    }
}