package com.walletsphere.app.presentation.login

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.walletsphere.app.domain.AuthUseCase
import com.walletsphere.app.domain.models.Status
import kotlinx.coroutines.launch

class LoginViewModel(context: Context): ViewModel() {
    private val authUseCase = AuthUseCase(context)

    val isLoading = MutableLiveData<Boolean>()
    val status = MutableLiveData<Status>()

    fun loginUser(username: String, password: String) {
        val userData = LoginUI(username, password)

        viewModelScope.launch {
            isLoading.postValue(true)

            status.postValue(authUseCase.loginUser(userData))

            isLoading.postValue(false)
        }
    }
}