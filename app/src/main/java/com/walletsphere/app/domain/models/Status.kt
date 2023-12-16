package com.walletsphere.app.domain.models

data class Status(
    val isSuccessful: Boolean,
    val message: String? = null
)
