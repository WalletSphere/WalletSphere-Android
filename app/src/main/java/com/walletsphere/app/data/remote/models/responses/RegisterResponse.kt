package com.walletsphere.app.data.remote.models.responses

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("username")
    val username: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("userRole")
    val userRole: String,

    @SerializedName("jwt")
    val token: String
)