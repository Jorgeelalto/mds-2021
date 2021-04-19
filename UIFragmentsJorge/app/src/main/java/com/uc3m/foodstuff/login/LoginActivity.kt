package com.uc3m.foodstuff.login
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.uc3m.foodstuff.MainActivity
import com.uc3m.foodstuff.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.login)
        val loading = findViewById<ProgressBar>(R.id.loading)

        val loginManager: LoginManager = LoginManager(this)

        login.isEnabled = true
        login.setOnClickListener {
            loading.visibility = View.VISIBLE
            Toast.makeText(
                applicationContext,
                "Holi",
                Toast.LENGTH_LONG
            ).show()
            loginManager.login(username.text.toString(), password.text.toString())
        }
    }
}