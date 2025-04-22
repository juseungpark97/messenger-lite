// src/main/kotlin/dev/juseung/messengerlite/MessengerApplication.kt
package dev.juseung.messengerlite

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MessengerApplication

fun main(args: Array<String>) {
    runApplication<MessengerApplication>(*args)
}

