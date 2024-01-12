package com.walletsphere.app.domain.utils

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.util.Base64

object JwtUtils {
    data class JWTPayload(
        @SerializedName("sub")
        val sub: String,

        @SerializedName("userId")
        val userId: Long,

        @SerializedName("iat")
        val iat: Long,

        @SerializedName("exp")
        val exp: Long
    )

    private val gson = Gson()

    fun isTokenExpired(token: String): Boolean =
        extractTokenPayload(token).let { (System.currentTimeMillis() / 1000) >= it.exp }

    private fun extractTokenPayload(token: String): JWTPayload =
        gson.fromJson(fromBase64(token.split('.')[1]), JWTPayload::class.java)

    private fun fromBase64(data: String): String =
        String(Base64.getDecoder().decode(data))
}