package com.walletsphere.app.presentation.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LoginViewModelFactory(private val context: Context): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when(modelClass) {
            LoginViewModel::class.java -> LoginViewModel(context) as T
            else -> super.create(modelClass)
        }
    }
}