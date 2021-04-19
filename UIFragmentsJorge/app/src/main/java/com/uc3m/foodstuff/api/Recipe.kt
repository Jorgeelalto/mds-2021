package com.uc3m.foodstuff.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Recipe (
    @SerializedName("id")
    @Expose
    val id: String = "",
    @SerializedName("user")
    @Expose
    val user: String = "",
    @SerializedName("name")
    @Expose
    val name: String = "",
    @SerializedName("description")
    @Expose
    val description: String = "",
    @SerializedName("time")
    @Expose
    val time: Float = 0.0f,
    @SerializedName("ingredients")
    @Expose
    val ingredients: Map<String, String>,
    @SerializedName("steps")
    @Expose
    val steps: List<String>
)