package com.walletsphere.app.data.remote.models.requests

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("username")
    val username: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("deviceType")
    val deviceType: String = "APP",
)
