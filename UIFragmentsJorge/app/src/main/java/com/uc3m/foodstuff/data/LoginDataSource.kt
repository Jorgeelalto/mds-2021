package com.uc3m.foodstuff.data

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.uc3m.foodstuff.data.model.LoggedInUser
import java.io.IOException
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

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


/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
        val db = Firebase.firestore
        // Get the pass of the user with that username
        val user = db.collection("users").document(username)
        var documentSnapshot: DocumentSnapshot? = null
        user.get().addOnSuccessListener { document ->
            if (document != null) {
                documentSnapshot = document
                Log.d(TAG, "DocumentSnapshot data: ${document.data}")
            } else {
                Log.d(TAG, "No such document")
            }
        }
        // Check if the user exists
        if (documentSnapshot == null) {
            //TODO work!!!!!!!!!
            //Toast.makeText(this, "The user specified does not exist", Toast.LENGTH_SHORT)
            return Result.Error(IOException("User document ${username} not found"))
        }
        // Check if the user has a password field
        if (documentSnapshot!!.get("pass") == null || documentSnapshot!!.get("pass") == null) {
            //TODO work!!!!!!!!!
            //Toast.makeText(this, "The user does not have a password", Toast.LENGTH_SHORT)
            return Result.Error(IOException("User document ${username} does not have a password"))
        }
        // Check if the password is correct
        if (hash(documentSnapshot!!.get("pass") as String) != password) {
            //TODO work!!!!!!!!!
            //Toast.makeText(this, "You have entered an incorrect password", Toast.LENGTH_SHORT)
            return Result.Error(IOException("Incorrect password for ${username}"))
        }

        // TODO Possibly change the UUID?
        val loggedInUser = LoggedInUser(java.util.UUID.randomUUID().toString(), username)
        return Result.Success(loggedInUser)
        /*
        try {
            // TODO: handle loggedInUser authentication
            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Jane Doe")
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }*/
    }

    fun logout() {
        // TODO: revoke authentication
    }
}