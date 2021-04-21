package com.uc3m.foodstuff.login

import android.content.Intent
import com.uc3m.foodstuff.MainActivity
import android.content.Context
import android.util.Log
import android.view.Gravity
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.uc3m.foodstuff.R
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

const val TAG = "LoginManager"
const val USER_LEN = 16
const val PASS_LEN = 64

class LoginManager(var context: Context) {

    private val loggedUserRepo: LoggedUserRepo = LoggedUserRepo(context)
    //private val sharedPreferences = context.getSharedPreferences("com.uc3m.foodstuff.PREF", Context.MODE_PRIVATE)

    private var pass: String = ""


    // Generate an hexadecimal SHA 1 string
    private fun hash(input: String): String {
        return try {
            val md = MessageDigest.getInstance("SHA-1")
            val messageDigest = md.digest(input.toByteArray())
            val no = BigInteger(1, messageDigest)
            var hashtext = no.toString(16)
            while (hashtext.length < 40) {
                hashtext = "0$hashtext"
            }
            hashtext
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e)
        }
    }


    private fun validate(username: String, password: String): Boolean {

        // Check the lengths
        if (username.isEmpty()) {
            Log.d(TAG, "Username is empty")
            Toast.makeText(context, "The username field is empty", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password.isEmpty()) {
            Log.d(TAG, "Password is empty")
            Toast.makeText(context, "The password field is empty", Toast.LENGTH_SHORT).show()
            return false
        }
        if (username.length > USER_LEN) {
            Log.d(TAG, "Username longer than $USER_LEN characters")
            Toast.makeText(context, "The username is too long (more than $USER_LEN characters)", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password.length > PASS_LEN) {
            Log.d(TAG, "Password longer than $PASS_LEN characters")
            Toast.makeText(context, "The password is too long (more than $PASS_LEN characters)", Toast.LENGTH_SHORT).show()
            return false
        }

        // Check whether the fields have weird characters or not
        if (!username.matches(Regex("[a-zA-Z0-9-_.]+"))) {
            Log.d(TAG, "Username doesn't match the regex")
            Toast.makeText(context, "The username contains characters that are not allowed. " +
                    "Please use letters, numbers, periods, hyphens and underscores only", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!password.matches(Regex("[a-zA-Z0-9-_.]+"))) {
            Log.d(TAG, "Password doesn't match the regex")
            Toast.makeText(context, "The password contains characters that are not allowed. " +
                    "Please use letters, numbers, periods, hyphens and underscores only", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    fun register(username: String, password: String) {
        // Check if the data is correct
        if (!validate(username, password)) {
            return
        }

        val db = Firebase.firestore

        // Create a new user with the password
        val user = hashMapOf("pass" to hash(password))

        // Add a new document with a generated ID
        db.collection("users").document(username).set(user)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: $documentReference")
                    Toast.makeText(context, "User registered correctly, logging in...", Toast.LENGTH_SHORT).show()
                    login(username, password)
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                    Toast.makeText(context, "User could not be registered", Toast.LENGTH_SHORT).show()
                }
    }

    fun login(username: String, password: String) {
        // Check if the data is correct
        if (!validate(username, password)) {
            return
        }

        val db = Firebase.firestore

        // Get the pass of the user with that username
        val user = db.collection("users").document(username)

        user.get().addOnSuccessListener { document ->
            if (document == null) {
                Log.d(TAG, "No such document")
                Toast.makeText(context, "User does not exist", Toast.LENGTH_SHORT).show()
            } else if (document.data == null) {
                Log.d(TAG, "Document is empty")
                Toast.makeText(context, "User does not exist or its data is corrupt", Toast.LENGTH_SHORT).show()
            } else if (document.get("pass") == null) {
                Log.d(TAG, "Document has no \"pass\" field")
                Toast.makeText(context,"User does not have a password and/or its data is corrupt", Toast.LENGTH_SHORT).show()
            } else if (document.get("pass") == "") {
                Log.d(TAG, "Document's \"pass\" field is empty")
                Toast.makeText(context,"User does not have a password and/or its data is corrupt", Toast.LENGTH_SHORT).show()
            } else {
                Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                pass = document.get("pass") as String
                Log.d(TAG, "pass: $pass")
                manageLogin(username, password)
            }
        }
    }

    private fun manageLogin(username: String, password: String): Boolean {
        if (pass == "") {
            Log.d(TAG, "There is no password")
            Toast.makeText(context, "Password is empty", Toast.LENGTH_SHORT).show()
            return false
        }
        // Check if the password is correct
        if (hash(password) != pass) {
            Log.d(TAG, "Password hash and firebase hash do not match")
            Log.d(TAG, "Password hash: ${hash(password)}")
            Log.d(TAG, "Firebase hash: $pass")
            Toast.makeText(context, "You have entered an incorrect password", Toast.LENGTH_SHORT).show()
            return false
        }
        loggedUserRepo.setLoggedUser(username)
        startActivity(context, Intent(context, MainActivity::class.java), null)
        return true
    }
}
