package com.walletsphere.app.data.remote.models.responses

import com.google.gson.annotations.SerializedName
import com.walletsphere.app.data.remote.models.enums.ExchangerCode

data class CurrencyResponse(
    @SerializedName("currencyCode")
    val currencyCode: String,

    @SerializedName("amount")
    val amount: Double,
)

data class BalanceResponse(
    @SerializedName("id")
    val id: Long,

    @SerializedName("balanceName")
    val balanceName: String,

    // TODO replace with LocalDateTime
    @SerializedName("lastTimeWasUpdated")
    val lastTimeUpdated: String,

    @SerializedName("code")
    val code: ExchangerCode,

    @SerializedName("currencies")
    val currencies: List<CurrencyResponse>,

    @SerializedName("user_id")
    val userID: Long,
)
