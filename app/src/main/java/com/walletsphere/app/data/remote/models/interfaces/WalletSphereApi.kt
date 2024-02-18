package com.walletsphere.app.data.remote.models.interfaces

import com.walletsphere.app.data.remote.models.requests.AddIntegrationRequest
import com.walletsphere.app.data.remote.models.requests.RegisterRequest
import com.walletsphere.app.data.remote.models.responses.RegisterResponse
import com.walletsphere.app.data.remote.models.requests.LoginRequest
import com.walletsphere.app.data.remote.models.responses.AddIntegrationResponse
import com.walletsphere.app.data.remote.models.responses.BalanceResponse
import com.walletsphere.app.data.remote.models.responses.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface WalletSphereApi {

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("exchangers/api-keys")
    suspend fun addIntegration(@Body request: AddIntegrationRequest, @Header("Authorization") token: String)
    : Response<AddIntegrationResponse>

    @POST("exchangers/synchronize/balance")
    suspend fun synchronizeBalance(@Header("Authorization") token: String): Response<List<BalanceResponse>>

    @GET("exchangers/balance/all")
    suspend fun getAllBalances(@Header("Authorization") token: String): Response<List<BalanceResponse>>
}