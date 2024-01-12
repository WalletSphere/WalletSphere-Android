package com.walletsphere.app.data.remote.models.responses

import com.google.gson.annotations.SerializedName
import com.walletsphere.app.data.remote.models.interfaces.AuthResponse

data class RegisterResponse(
    @SerializedName("username")
    override val username: String,

    @SerializedName("jwt")
    override val token: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("userRole")
    val userRole: String

): AuthResponse