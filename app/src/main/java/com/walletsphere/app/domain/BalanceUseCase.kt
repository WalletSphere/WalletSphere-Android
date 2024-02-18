package com.walletsphere.app.domain

import android.content.Context
import com.walletsphere.app.data.UserRepository
import com.walletsphere.app.data.WalletSphereRepository
import com.walletsphere.app.data.remote.Result
import com.walletsphere.app.data.remote.models.responses.BalanceResponse
import com.walletsphere.app.presentation.portfolio.TokenUI
import java.math.BigDecimal
import java.math.RoundingMode

class BalanceUseCase(context: Context) {
    private val walletSphereRepository = WalletSphereRepository
    private val userRepository = UserRepository(context)

    // TODO Refactor using interceptors
    private fun addBearerToToken(token: String) = "Bearer $token"

    suspend fun getAllBalances() =
        userRepository.getAuthorizedUser()
            ?.let { walletSphereRepository.getAllBalances(addBearerToToken(it.token)) }
            ?.let { handleGetAllBalancesResponse(it) }
            ?.let { sumAllTokens(it) }
            ?.let { convertHashMapToTokenUi(it) }

    private fun handleGetAllBalancesResponse(result: Result<List<BalanceResponse>>) =
        when (result) {
            is Result.Success -> {
                result.data
            }

            is Result.ErrorTimeOut -> null
            else -> null
        }

    private fun sumAllTokens(balances: List<BalanceResponse>) =
        HashMap<String, Double>().also { resultHashMap ->
            balances.forEach {
                it.currencies.forEach { currency ->
                    resultHashMap.putIfAbsent(currency.currencyCode, currency.amount).also { amount ->
                        if (amount != null) resultHashMap[currency.currencyCode] = amount + currency.amount
                    }
                }
            }
        }

    private fun convertHashMapToTokenUi(tokens: HashMap<String, Double>) =
        tokens.map {
            TokenUI(
                it.key,
                BigDecimal(it.value).setScale(6, RoundingMode.HALF_DOWN).toPlainString()
            )
        }

}