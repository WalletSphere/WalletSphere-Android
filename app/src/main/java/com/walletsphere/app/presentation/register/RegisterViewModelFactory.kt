package com.walletsphere.app.presentation.register

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RegisterViewModelFactory(private val context: Context): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when(modelClass) {
            RegisterViewModel::class.java -> RegisterViewModel(context) as T
            else -> super.create(modelClass)
        }
}