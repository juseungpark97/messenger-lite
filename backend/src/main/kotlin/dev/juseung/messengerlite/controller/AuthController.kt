package dev.juseung.messengerlite.controller

import dev.juseung.messengerlite.config.jwt.JwtTokenProvider
import dev.juseung.messengerlite.domain.dto.SignupRequest
import dev.juseung.messengerlite.domain.service.UserService
import dev.juseung.messengerlite.domain.User
import dev.juseung.messengerlite.dto.LoginRequest
import dev.juseung.messengerlite.dto.LoginResponse
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.Duration

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val userService: UserService,
    private val jwtTokenProvider: JwtTokenProvider,
    private val redisTemplate: RedisTemplate<String, String>
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


    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<LoginResponse> {
        val user = userService.authenticate(request.email, request.password)

        user.id?.let {
            val accessToken = jwtTokenProvider.createAccessToken(it)
            val refreshToken = jwtTokenProvider.createRefreshToken()

            redisTemplate.opsForValue().set("refresh:$it", refreshToken, Duration.ofDays(7))

            return ResponseEntity.ok(LoginResponse(accessToken, refreshToken))
        } ?: throw IllegalStateException("회원 ID가 존재하지 않습니다.")
    }
}