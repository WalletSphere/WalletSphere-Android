package com.walletsphere.app.data.remote.models.responses

import com.google.gson.annotations.SerializedName
import com.walletsphere.app.data.remote.models.enums.RegistrationStatus

data class AddIntegrationResponse(
    @SerializedName("balanceId")
    val balanceID: Long,

    @SerializedName("userId")
    val userID: Long,

    @SerializedName("status")
    val status: RegistrationStatus
)
