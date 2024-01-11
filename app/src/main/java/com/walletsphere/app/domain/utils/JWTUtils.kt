package com.walletsphere.app.domain.utils

import com.google.gson.Gson
import java.util.Base64

object JWTUtils {
    data class JWTPayload(
        val sub: String,
        val userId: Long,
        val iat: Long,
        val exp: Long
    )

    fun isTokenExpired(token: String): Boolean {
        val parts = token.split('.')
        val decoded = String(Base64.getDecoder().decode(parts[1]))
        val payload = Gson().fromJson(decoded, JWTPayload::class.java)

        return (System.currentTimeMillis() / 1000) >= payload.exp
    }
}