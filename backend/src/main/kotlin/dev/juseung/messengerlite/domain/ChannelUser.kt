package dev.juseung.messengerlite.domain

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "channel_users")
data class ChannelUser(
    /**
     * 기본 키
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    /**
     * 참여한 채널
     */
    @ManyToOne
    @JoinColumn(name = "channel_id")
    val channel: Channel,

    /**
     * 참여 유저
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

    /**
     * 유저가 설정한 커스텀 방 이름
     */
    @Column(name = "custom_name")
    val customName: String? = null,

    /**
     * 권한 (예: ADMIN, MEMBER)
     */
    @Column(nullable = false)
    val role: String = "MEMBER",

    /**
     * 알림 끄기 여부
     */
    @Column(name = "alarm_off")
    val alarmOff: Boolean = false,

    /**
     * 마지막 읽은 메시지 ID
     */
    @Column(name = "last_read_message_id")
    val lastReadMessageId: String? = null,

    /**
     * 채널 참여 시각
     */
    @Column(name = "joined_at", nullable = false)
    val joinedAt: LocalDateTime = LocalDateTime.now()
)