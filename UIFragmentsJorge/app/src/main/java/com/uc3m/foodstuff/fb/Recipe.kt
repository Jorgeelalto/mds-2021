package com.uc3m.foodstuff.fb

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.uc3m.foodstuff.login.TAG

data class Recipe (
        var user: String = "",
        var name: String = "",
        var description: String = "",
        var non_vegetarian: Boolean = true,
        var vegetarian: Boolean = false,
        var vegan: Boolean = false,
        var gluten_free: Boolean = false,
        var cheap: Boolean = false,
        var dairy_free: Boolean = false,
        var time: Float = 0.0f,
        var ingredients: String = "",
        var instructions: String = ""
)
