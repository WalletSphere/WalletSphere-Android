package com.walletsphere.app.data.local

import android.content.Context
import com.walletsphere.app.data.local.models.AuthorizedUser

class SharedPref(context: Context) {
    private val prefsName = "wallet_sphere_preferences"

    private val authorizedUserNameKey = "authorized_user_name"
    private val authorizedUserTokenKey = "authorized_user_token"

    private val sharedPreferences = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    fun setAuthorizedUser(user: AuthorizedUser) =
        with(editor) {
            putString(authorizedUserNameKey, user.name)
            putString(authorizedUserTokenKey, user.token)

            commit()
        }

    fun getAuthorizedUser(): AuthorizedUser? {
        val name = sharedPreferences.getString(authorizedUserNameKey, null)
        val token = sharedPreferences.getString(authorizedUserTokenKey, null)

        return if(name != null && token != null) AuthorizedUser(name, token) else null
    }

    fun clearAuthorizedUser() =
        with(editor) {
            putString(authorizedUserNameKey, null)
            putString(authorizedUserTokenKey, null)

            commit()
        }

}