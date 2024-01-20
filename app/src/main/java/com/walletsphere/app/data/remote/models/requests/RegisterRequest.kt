package com.walletsphere.app.data.remote.models.requests

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("username")
    val username: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("acceptTC")
    val acceptTC: Boolean = true,

    @SerializedName("deviceType")
    val deviceType: String = "APP",
)
