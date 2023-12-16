package com.walletsphere.app.domain

import android.content.Context
import com.walletsphere.app.data.UserDataRepository
import com.walletsphere.app.data.WalletSphereRepository
import com.walletsphere.app.data.local.models.AuthorizedUser
import com.walletsphere.app.data.remote.Result
import com.walletsphere.app.data.remote.models.requests.RegisterRequest
import com.walletsphere.app.data.remote.models.requests.LoginRequest
import com.walletsphere.app.domain.models.Status
import com.walletsphere.app.presentation.login.LoginUI
import com.walletsphere.app.presentation.register.RegisterUI
class AuthUseCase(context: Context) {
    private val walletSphereRepository = WalletSphereRepository
    private val userDataRepository = UserDataRepository(context)

    suspend fun registerUser(userData: RegisterUI): Status {
        val request = RegisterRequest(
            userData.username,
            userData.password,
            userData.email
        )

        val result = walletSphereRepository.register(request)

        return when(result) {
            is Result.Success -> {
                userDataRepository.setAuthorizedUser(
                    AuthorizedUser(
                        result.data.username,
                        result.data.token
                    )
                )

                Status(true)
            }

            is Result.ErrorTimeOut -> Status(false, "Error Time Out Exception")
            else -> Status(false, "Unknown Exception")
        }

    }

    suspend fun loginUser(userData: LoginUI): Status {
        val request = LoginRequest(
            userData.username,
            userData.password
        )

        val result = walletSphereRepository.login(request)

        return when(result) {
            is Result.Success -> {
                userDataRepository.setAuthorizedUser(
                    AuthorizedUser(
                        result.data.username,
                        result.data.token
                    )
                )

                Status(true)
            }

            is Result.ErrorTimeOut -> Status(false, "Error Time Out Exception")
            else -> Status(false, "Unknown Exception")
        }
    }
}