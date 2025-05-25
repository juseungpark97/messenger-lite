package dev.juseung.messengerlite.dto

data class KafkaMessage (
    val type : String,
    val payload: Map<String, Any>
)