package dev.juseung.messengerlite.dto

data class UserDto (
    val id: Long,
    val name: String,
    val profileImageUrl: String?,
    val statusMessage: String?
)
