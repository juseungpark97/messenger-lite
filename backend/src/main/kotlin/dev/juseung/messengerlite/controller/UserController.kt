package dev.juseung.messengerlite.controller

import dev.juseung.messengerlite.domain.dto.SignupRequest
import dev.juseung.messengerlite.domain.service.UserService
import dev.juseung.messengerlite.domain.User
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/auth")
class UserController(
    private val userService: UserService
) {

    @PostMapping("/signup")
    fun signup(@RequestBody request: SignupRequest): ResponseEntity<User> {
        val user = userService.registerUser(
            email = request.email,
            password = request.password,
            name = request.name
        )
        return ResponseEntity.ok(user)
    }
}