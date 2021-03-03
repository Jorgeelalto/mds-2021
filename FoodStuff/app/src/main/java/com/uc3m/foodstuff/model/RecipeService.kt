package com.uc3m.foodstuff.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeService {
    // TODO add the rest of the methods
    @GET("/recipes/{id}.json")
    fun getRecipe(@Path(value = "id") id: Int): Call<Recipe>
}