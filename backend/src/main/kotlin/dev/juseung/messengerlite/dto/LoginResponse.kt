package dev.juseung.messengerlite.dto

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String
)