package com.uc3m.uifragments.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/users/{id}.json")
    fun getUser(@Path(value = "name") name: Int): Call<User>

    @GET("/users.json")
    fun getAllUsers(): Call<List<User>>

    @GET("/recipes/{id}.json")
    fun getRecipe(@Path(value = "id") id: String): Call<Recipe>

    @GET("recipes.json")
    fun getAllRecipes(): Call<List<Recipe>>
}