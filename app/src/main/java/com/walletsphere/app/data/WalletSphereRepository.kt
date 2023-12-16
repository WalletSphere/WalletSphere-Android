package com.walletsphere.app.data

import com.walletsphere.app.data.remote.ApiFactory
import com.walletsphere.app.data.remote.Result
import com.walletsphere.app.data.remote.models.requests.RegisterRequest
import com.walletsphere.app.data.remote.models.requests.LoginRequest
import com.walletsphere.app.data.remote.models.responses.LoginResponse
import com.walletsphere.app.data.remote.models.responses.RegisterResponse
import retrofit2.Response
import java.lang.Exception
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object WalletSphereRepository {
    private val api = ApiFactory.walletSphereApi

    suspend fun register(request: RegisterRequest): Result<RegisterResponse> {
        return safetyCall { api.register(request) }
    }

    suspend fun login(request: LoginRequest): Result<LoginResponse> {
        return safetyCall { api.login(request) }
    }

    private suspend fun <T> safetyCall(call: suspend () -> Response<T>): Result<T> {
        try {
            val result = call.invoke()

            val response = if (result.isSuccessful) {
                Result.Success(result.body()!!)

            } else {
                Result.Error(result.code(), result.toString())
            }

            return response

        } catch (e: Exception) {
            return when(e) {
                is SocketTimeoutException -> Result.ErrorTimeOut
                is UnknownHostException -> Result.ErrorUnknown

                else -> Result.ErrorUnknown
            }
        }
    }
}