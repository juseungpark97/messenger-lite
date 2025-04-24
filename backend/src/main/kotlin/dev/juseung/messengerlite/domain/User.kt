package dev.juseung.messengerlite.domain

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "users")
data class User(
    /**
     * 사용자 기본 키
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    /**
     * 로그인에 사용하는 이메일 (중복 불가)
     */
    @Column(nullable = false, unique = true)
    val email: String,

    /**
     * 암호화된 비밀번호
     */
    @Column(nullable = false)
    val password: String,

    /**
     * 사용자 이름 (표시용)
     */
    @Column(nullable = false, length = 50)
    val name: String,

    /**
     * 프로필 이미지 URL
     */
    @Column(name = "profile_image_url")
    val profileImageUrl: String? = null,

    /**
     * 상태 메시지 (예: 회의 중)
     */
    @Column(name = "status_message")
    val statusMessage: String? = null,

    /**
     * 마지막 로그인 시간
     */
    @Column(name = "last_login_at")
    val lastLoginAt: LocalDateTime? = null,

    /**
     * 선호 언어 코드 (예: ko, en)
     */
    @Column(name = "language", length = 10)
    val language: String? = "ko",

    /**
     * 삭제된 사용자 여부 (소프트 삭제용)
     */
    @Column(name = "is_deleted")
    val isDeleted: Boolean = false
)