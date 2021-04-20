package com.uc3m.foodstuff.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiRecipe (
    @SerializedName("publisher")
    @Expose
    val publisher: String = "",
    @SerializedName("ingredients")
    @Expose
    val ingredients: ArrayList<String>,
    @SerializedName("source_url")
    @Expose
    val source_url: String = "",
    @SerializedName("recipe_id")
    @Expose
    val recipe_id: String = "",
    @SerializedName("image_url")
    @Expose
    val image_url: String = "",
    @SerializedName("social_rank")
    @Expose
    val social_rank: Float = 0.0f,
    @SerializedName("publisher_url")
    @Expose
    val publisher_url: String = "",
    @SerializedName("title")
    @Expose
    val title: String = ""
)


data class SearchResponse (
        @SerializedName("count")
        @Expose
        val count: Int = 0,
        @SerializedName("recipes")
        @Expose
        val recipes: List<ApiRecipe>
)