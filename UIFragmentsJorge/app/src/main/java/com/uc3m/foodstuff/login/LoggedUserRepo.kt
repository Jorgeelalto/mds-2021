package com.uc3m.foodstuff.login

import android.content.Context

const val REPO = "com.uc3m.foodstuff.PREF"


class LoggedUserRepo(val context: Context) {

    private val sharedPreferences = context.getSharedPreferences(REPO, Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    fun setLoggedUser(username: String) {
        editor.putString("user", username)
        editor.commit()
    }

    fun getLoggedUser(): String {
        return sharedPreferences.getString("user", "") ?: ""
    }
}