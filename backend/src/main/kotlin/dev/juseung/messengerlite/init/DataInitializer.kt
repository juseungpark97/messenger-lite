package dev.juseung.messengerlite.init

import dev.juseung.messengerlite.domain.*
import dev.juseung.messengerlite.domain.repository.ChannelRepository
import dev.juseung.messengerlite.domain.repository.ChannelUserRepository
import dev.juseung.messengerlite.domain.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.boot.CommandLineRunner
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class DataInitializer(
    private val userRepository: UserRepository,
    private val channelRepository: ChannelRepository,
    private val channelUserRepository: ChannelUserRepository,
    private val passwordEncoder: PasswordEncoder
) : CommandLineRunner {

    @Transactional
    override fun run(vararg args: String?) {
        if (userRepository.count() == 0L) {
            val user = userRepository.save(
                User(
                    email = "test@local.com",
                    password = passwordEncoder.encode("1234"),
                    name = "김민수",
                    profileImageUrl = "https://i.pravatar.cc/150?img=3",
                    statusMessage = "대기업 준비중"
                )
            )

            val user2 = userRepository.save(
                User(
                    email = "user2@local.com",
                    password = passwordEncoder.encode("1234"),
                    name = "김민지",
                    profileImageUrl = "https://i.pravatar.cc/150?img=12",
                    statusMessage = "지금은 좀 바빠요"
                )
            )

        }
    }
}