package com.walletsphere.app.data.remote

import com.walletsphere.app.data.remote.models.requests.RegisterRequest
import com.walletsphere.app.data.remote.models.responses.RegisterResponse
import com.walletsphere.app.data.remote.models.requests.LoginRequest
import com.walletsphere.app.data.remote.models.responses.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface WalletSphereApiInterface {

    @POST("auth/register")
    suspend fun register(@Body model: RegisterRequest): Response<RegisterResponse>

    @POST("auth/login")
    suspend fun login(@Body model: LoginRequest): Response<LoginResponse>
}