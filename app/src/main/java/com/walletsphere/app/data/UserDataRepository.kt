package com.walletsphere.app.data

import android.content.Context
import com.walletsphere.app.data.local.SharedPref
import com.walletsphere.app.data.local.models.AuthorizedUser

class UserDataRepository(context: Context) {
    private val sharedPref = SharedPref(context)

    fun setAuthorizedUser(user: AuthorizedUser) {
        sharedPref.setAuthorizedUser(user)
    }

    fun getAuthorizedUser(): AuthorizedUser? {
        return sharedPref.getAuthorizedUser()
    }
}