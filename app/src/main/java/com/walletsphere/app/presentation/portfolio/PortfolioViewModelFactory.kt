package com.walletsphere.app.presentation.portfolio

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PortfolioViewModelFactory(private val context: Context): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when(modelClass) {
            PortfolioViewModel::class.java -> PortfolioViewModel(context) as T
            else -> super.create(modelClass)
        }
}