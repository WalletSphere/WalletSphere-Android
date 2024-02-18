package com.walletsphere.app.presentation.portfolio

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.walletsphere.app.domain.BalanceUseCase
import kotlinx.coroutines.launch

class PortfolioViewModel(context: Context) : ViewModel() {
    private val balanceUseCase = BalanceUseCase(context)

    var tokensList = MutableLiveData<List<TokenUI>>()

    fun getAllBalances() =
        viewModelScope.launch {
            tokensList.postValue(balanceUseCase.getAllBalances()!!)
        }
}