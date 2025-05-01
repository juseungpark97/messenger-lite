package dev.juseung.messengerlite.dto

import java.time.LocalDateTime

data class ErrorResponse(
    val message: String,
    val code: String = "BAD_REQUEST",
    val timestamp: String = LocalDateTime.now().toString()
)