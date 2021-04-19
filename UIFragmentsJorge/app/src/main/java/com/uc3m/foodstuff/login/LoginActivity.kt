package com.uc3m.foodstuff.login
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import com.uc3m.foodstuff.R


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.login)
        val loading = findViewById<ProgressBar>(R.id.loading)

        val loginManager = LoginManager(this)

        login.isEnabled = true
        login.setOnClickListener {
            loading.visibility = View.VISIBLE
            loginManager.login(username.text.toString(), password.text.toString())
            loading.visibility = View.INVISIBLE
        }
    }
}