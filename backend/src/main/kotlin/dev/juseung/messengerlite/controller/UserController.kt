package dev.juseung.messengerlite.controller

import dev.juseung.messengerlite.domain.repository.UserRepository
import dev.juseung.messengerlite.dto.UserDto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userRepository: UserRepository
) {

    @GetMapping
    fun getAllUsers(): List<UserDto> {
        return userRepository.findAll().map {
            user -> UserDto(
                id = user.id!!,
                name = user.name,
                profileImageUrl = user.profileImageUrl,
                statusMessage = user.statusMessage
            )
        }
    }
}