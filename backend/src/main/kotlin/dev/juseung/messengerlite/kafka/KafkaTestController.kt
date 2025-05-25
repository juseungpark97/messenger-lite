package dev.juseung.messengerlite.kafka

import dev.juseung.messengerlite.dto.KafkaMessage
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test/kafka")
class KafkaTestController(
    private val kafkaProducer: KafkaProducer
) {
    @PostMapping("/send")
    fun sendTestMessage(@RequestParam message: String): String {
        val testMessage = KafkaMessage(
            type = "chat",
            payload = mapOf(
                "senderId" to "U123",
                "content" to message,
                "timestamp" to System.currentTimeMillis()
            )
        )
        kafkaProducer.send("message-topic", testMessage)
        return "Kafka 테스트 메시지 전송 완료"
    }
}