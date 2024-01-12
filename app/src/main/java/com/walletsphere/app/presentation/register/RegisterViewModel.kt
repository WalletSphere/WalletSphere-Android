package com.walletsphere.app.presentation.register

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.walletsphere.app.domain.AuthUseCase
import com.walletsphere.app.domain.models.Status
import kotlinx.coroutines.launch


class RegisterViewModel(context: Context): ViewModel() {
    private val authUseCase = AuthUseCase(context)

    val isLoading = MutableLiveData<Boolean>()
    val status = MutableLiveData<Status>()

    fun registerUser(username: String, password: String, email: String) =
        viewModelScope.launch {
            isLoading.postValue(true)

            status.postValue(
                RegisterUI(username, password, email).let { authUseCase.registerUser(it) }
            )

            isLoading.postValue(false)
        }

}