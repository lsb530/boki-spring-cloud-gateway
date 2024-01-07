package boki.tistory.apikeyservice.service

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class RedisService(
    private val redisTemplate: RedisTemplate<String, Any>
) {
    fun saveToRedis(key: String, value: Any, duration: Duration?) {
        duration?.let {
            redisTemplate.opsForValue().set(key, value, it)
        } ?: redisTemplate.opsForValue().set(key, value)
    }

    fun getFromRedis(key: String): Any? {
        return redisTemplate.opsForValue()[key]
    }

    fun existsInRedis(key: String): Boolean {
        return redisTemplate.hasKey(key) ?: false
    }

}