package com.walletsphere.app.domain

import android.content.Context
import com.walletsphere.app.data.UserRepository
import com.walletsphere.app.data.WalletSphereRepository
import com.walletsphere.app.data.local.models.AuthorizedUser
import com.walletsphere.app.data.remote.Result
import com.walletsphere.app.data.remote.models.interfaces.AuthResponse
import com.walletsphere.app.data.remote.models.requests.RegisterRequest
import com.walletsphere.app.data.remote.models.requests.LoginRequest
import com.walletsphere.app.data.remote.models.responses.LoginResponse
import com.walletsphere.app.data.remote.models.responses.RegisterResponse
import com.walletsphere.app.domain.models.Status
import com.walletsphere.app.domain.utils.JwtUtils
import com.walletsphere.app.presentation.login.LoginUI
import com.walletsphere.app.presentation.register.RegisterUI

class AuthUseCase(context: Context) {
    private val walletSphereRepository = WalletSphereRepository
    private val userRepository = UserRepository(context)

    suspend fun registerUser(userData: RegisterUI): Status =
        RegisterRequest(userData.username, userData.password, userData.email)
            .let { walletSphereRepository.register(it) }
            .let { handleRegisterResponse(it) }

    private fun handleRegisterResponse(result: Result<RegisterResponse>) =
        when (result) {
            is Result.Success -> {
                handleSuccessAuthorization(result)
            }

            is Result.ErrorTimeOut -> Status(false, "Error Time Out Exception")
            else -> Status(false, "Unknown Exception")
        }

    suspend fun loginUser(userData: LoginUI): Status =
        LoginRequest(userData.username, userData.password)
            .let { walletSphereRepository.login(it) }
            .let { handleLoginResponse(it) }

    private fun handleLoginResponse(result: Result<LoginResponse>) =
        when (result) {
            is Result.Success -> {
                handleSuccessAuthorization(result)
            }

            is Result.ErrorTimeOut -> Status(false, "Error Time Out Exception")
            else -> Status(false, "Unknown Exception")
        }

    private fun handleSuccessAuthorization(result: Result.Success<AuthResponse>): Status {
        userRepository.setAuthorizedUser(
            AuthorizedUser(result.data.username, result.data.token)
        )

        return Status(true)
    }

    fun checkIfUserAuthorized(): Boolean = userRepository.getAuthorizedUser()?.let {
        !JwtUtils.isTokenExpired(it.token).also { isExpired ->
            if(isExpired) userRepository.clearAuthorizedUser()
        }
    } ?: false
}