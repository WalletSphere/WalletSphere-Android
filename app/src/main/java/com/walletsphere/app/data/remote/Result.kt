package com.walletsphere.app.data.remote

sealed class Result<out T> {
    data class Success<out T>(val data: T): Result<T>()

    data class Error(val code: Int, val msg: String): Result<Nothing>()

    object ErrorTimeOut: Result<Nothing>()

    object ErrorUnknown: Result<Nothing>()

}
