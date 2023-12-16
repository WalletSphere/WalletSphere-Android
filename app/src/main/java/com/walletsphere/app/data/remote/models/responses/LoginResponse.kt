package com.walletsphere.app.data.remote.models.responses

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("username")
    val username: String,

    @SerializedName("jwt")
    val token: String,
)
