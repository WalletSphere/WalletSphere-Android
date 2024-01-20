package com.walletsphere.app.presentation.welcome

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class WelcomeViewModelFactory(private val context: Context): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when(modelClass) {
            WelcomeViewModel::class.java -> WelcomeViewModel(context) as T
            else -> super.create(modelClass)
        }
}