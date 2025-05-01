package dev.juseung.messengerlite.domain.service

import dev.juseung.messengerlite.domain.User
import dev.juseung.messengerlite.domain.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun registerUser(email: String, password: String, name: String): User {
        if (userRepository.findByEmail(email) != null) {
            throw IllegalArgumentException("이미 사용 중인 이메일입니다.")
        }

        val encodedPassword = passwordEncoder.encode(password)
        val user = User(email = email, password = encodedPassword, name = name)
        return userRepository.save(user)
    }
}