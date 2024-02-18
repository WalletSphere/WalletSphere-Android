package com.walletsphere.app.data.remote.models.requests

import com.google.gson.annotations.SerializedName
import com.walletsphere.app.data.remote.models.enums.ExchangerCode

data class ApiKeysRequest(
    @SerializedName("publicKey")
    val publicKey: String,

    @SerializedName("secretKey")
    val secretKey: String
)

data class AddIntegrationRequest(
    @SerializedName("apiKeysReq")
    val apiKeysRequest: ApiKeysRequest,

    @SerializedName("code")
    val code: ExchangerCode,

    @SerializedName("balanceName")
    val balanceName: String,
)
