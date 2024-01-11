package com.walletsphere.app.presentation.welcome

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.walletsphere.app.domain.AuthUseCase
import kotlinx.coroutines.launch

class WelcomeViewModel(context: Context): ViewModel() {
    private val authUseCase = AuthUseCase(context)

    val status = MutableLiveData<Boolean>()

    fun checkIfUserAuthorized() {
        viewModelScope.launch {
            status.postValue(authUseCase.checkIfUserAuthorized())
        }
    }
}