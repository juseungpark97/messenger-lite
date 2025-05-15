package dev.juseung.messengerlite.config.jwt

import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtTokenProvider {

    private val secretKey: SecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512)

    private val accessTokenValidity = 1000L * 60 * 30       // 30분
    private val refreshTokenValidity = 1000L * 60 * 60 * 24 // 24시간

    fun createAccessToken(userId: Long): String {
        val now = Date()
        return Jwts.builder()
            .setSubject(userId.toString())
            .setIssuedAt(now)
            .setExpiration(Date(now.time + accessTokenValidity))
            .signWith(secretKey)
            .compact()
    }

    fun createRefreshToken(): String {
        val now = Date()
        return Jwts.builder()
            .setIssuedAt(now)
            .setExpiration(Date(now.time + refreshTokenValidity))
            .signWith(secretKey)
            .compact()
    }

    fun getUserIdFromToken(token: String): Long {
        return Jwts.parserBuilder().setSigningKey(secretKey).build()
            .parseClaimsJws(token).body.subject.toLong()
    }

    fun validateToken(token: String): Boolean {
        return try {
            Jwts.parserBuilder().setSigningKey(secretKey).build()
                .parseClaimsJws(token)
            true
        } catch (e: JwtException) {
            false
        }
    }
}