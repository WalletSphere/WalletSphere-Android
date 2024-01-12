package com.walletsphere.app.data.remote.models.responses

import com.google.gson.annotations.SerializedName
import com.walletsphere.app.data.remote.models.interfaces.AuthResponse

data class LoginResponse(
    @SerializedName("username")
    override val username: String,

    @SerializedName("jwt")
    override val token: String,

): AuthResponse
