package com.uc3m.foodstuff.login

import android.content.Intent
import com.uc3m.foodstuff.MainActivity
import android.content.Context
import androidx.core.content.ContextCompat.startActivity


class LoginManager(var context: Context) {

    fun login(user: String, password: String) {
        startActivity(context, Intent(context, MainActivity::class.java), null)
    }
}