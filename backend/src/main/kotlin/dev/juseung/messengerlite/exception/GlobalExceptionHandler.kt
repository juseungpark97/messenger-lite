package dev.juseung.messengerlite.exception

import dev.juseung.messengerlite.dto.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgument(ex: IllegalArgumentException): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse(message = ex.message ?: "잘못된 요청입니다.")
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun handleGeneralException(ex: Exception): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse(message = "서버 오류가 발생했습니다.")
        return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}