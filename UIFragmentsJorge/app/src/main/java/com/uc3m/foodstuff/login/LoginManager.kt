package com.uc3m.foodstuff.login

import android.content.Intent
import com.uc3m.foodstuff.MainActivity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class LoginManager(var context: Context) {

    private val tag = "LoginManager"

    private var pass: String = ""


    // Generate an hexadecimal SHA 1 string
    fun hash(input: String): String {
        return try {
            val md = MessageDigest.getInstance("SHA-1")
            val messageDigest = md.digest(input.toByteArray())
            val no = BigInteger(1, messageDigest)
            var hashtext = no.toString(16)
            while (hashtext.length < 32) {
                hashtext = "0$hashtext"
            }
            hashtext
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e)
        }
    }

    fun login(username: String, password: String) {
        val db = Firebase.firestore
        // Get the pass of the user with that username
        val user = db.collection("users").document(username)

        user.get().addOnSuccessListener { document ->
            if (document != null) {
                Log.d(tag, "DocumentSnapshot data: ${document.data}")
                pass = document.get("pass") as String
                Log.d(tag, "pass: $pass")
                manageLogin(username, password)
            } else {
                Log.d(tag, "No such document")
            }
        }
    }

    private fun manageLogin(username: String, password: String): Boolean {
        Log.d(tag, "Jelow")
        if (pass == "") {
            Log.d(tag, "There is no password")
            Toast.makeText(context, "Password is empty", Toast.LENGTH_SHORT)
            return false
        }
        // Check if the password is correct
        if (hash(password) != pass) {
            Log.d(tag, "Password hash and firebase hash do not match")
            Log.d(tag, "Password hash: ${hash(password)}")
            Log.d(tag, "Firebase hash: $pass")
            Toast.makeText(context, "You have entered an incorrect password", Toast.LENGTH_SHORT)
            return false
        }
        // TODO put the username somewhere (sharedpreferences or whatever)
        startActivity(context, Intent(context, MainActivity::class.java), null)
        return true
    }
}