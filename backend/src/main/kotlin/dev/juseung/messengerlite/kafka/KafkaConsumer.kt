package dev.juseung.messengerlite.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import dev.juseung.messengerlite.dto.KafkaMessage
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaConsumer(
    private val objectMapper: ObjectMapper
) {
    @KafkaListener(topics = ["message-topic"], groupId = "messenger-group")
    fun consume(raw: String) {
        val message = objectMapper.readValue(raw, KafkaMessage::class.java)
        println(" Kafka 수신 메시지: type=${message.type}, payload=${message.payload}")
    }
}