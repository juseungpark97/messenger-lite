package dev.juseung.messengerlite.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import dev.juseung.messengerlite.dto.KafkaMessage
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper
) {
    fun send(topic: String, message: KafkaMessage) {
        val json = objectMapper.writeValueAsString(message)
        kafkaTemplate.send(topic, json)
        println(" Kafka 메시지 전송됨: $json")
    }
}