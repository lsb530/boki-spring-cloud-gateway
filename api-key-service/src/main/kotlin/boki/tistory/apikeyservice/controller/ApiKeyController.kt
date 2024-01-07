package boki.tistory.apikeyservice.controller

import boki.tistory.apikeyservice.service.RedisService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Duration
import java.util.*

data class KeyInfo(
    val apiKey: UUID,
    val expired: Date,
    val ip: String
)

@RequestMapping("/api-key")
@RestController
class ApiKeyController(
    private val redisService: RedisService
) {
    @PostMapping("/register")
    fun registerKey(request: HttpServletRequest): ResponseEntity<KeyInfo> {
        var apiKey: UUID
        var exists: Boolean

        do {
            apiKey = UUID.randomUUID()
            exists = redisService.existsInRedis(apiKey.toString())
        } while (exists)

        val days30 = Duration.ofDays(30)
        redisService.saveToRedis(key = apiKey.toString(), value = true, days30)

        val now = Date()
        val expired = Date(now.time + days30.toMillis())
        val ip = request.remoteAddr

        return ResponseEntity.ok(KeyInfo(apiKey = apiKey, expired = expired, ip = ip))
    }
}