package com.uc3m.uifragments.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("name")
    @Expose
    val name: String = "",
    @SerializedName("surname")
    @Expose
    val surname: Map<Int, String>,
    @SerializedName("email")
    @Expose
    val email: String = "",
    @SerializedName("password")
    @Expose
    val password: String = "",
    @SerializedName("recipes")
    @Expose
    val recipes: List<Recipe>
)