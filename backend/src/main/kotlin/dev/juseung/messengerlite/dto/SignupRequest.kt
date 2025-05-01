package dev.juseung.messengerlite.domain.dto

data class SignupRequest(
    val email: String,
    val password: String,
    val name: String
)