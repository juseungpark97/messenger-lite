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
                    name = "테스트 유저"
                )
            )

            val channel = channelRepository.save(
                Channel(
                    name = "테스트 채널",
                    isGroup = false,
                    createdBy = user
                )
            )

            channelUserRepository.save(
                ChannelUser(
                    channel = channel,
                    user = user,
                    role = "MEMBER"
                )
            )

            println("개발용 더미 유저/채널/참여자 생성 완료")
        }
    }
}