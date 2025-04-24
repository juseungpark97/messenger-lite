package dev.juseung.messengerlite.domain

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "channels")
data class Channel(
    /**
     * 채널 기본 키
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    /**
     * 채널 공용 이름
     */
    @Column(nullable = false)
    val name: String,

    /**
     * 채널 설명
     */
    val description: String? = null,

    /**
     * 그룹 채널 여부
     */
    @Column(name = "is_group", nullable = false)
    val isGroup: Boolean = false,

    /**
     * 채널 생성자
     */
    @ManyToOne
    @JoinColumn(name = "created_by")
    val createdBy: User,

    /**
     * 생성 시각
     */
    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
)