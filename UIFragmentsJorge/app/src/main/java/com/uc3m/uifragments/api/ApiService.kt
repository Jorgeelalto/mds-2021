package com.uc3m.uifragments.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("/users.json")
    fun getAllUsers(): Call<List<User>>

    @GET("/users/{name}.json")
    fun getUser(@Path(value = "name") name: String): Call<User>

    @POST("/users/{name}.json")
    fun postUser(@Path(value = "name") name: String, @Body user: User): Call<User>


    @GET("recipes.json")
    fun getAllRecipes(): Call<List<Recipe>>

    @GET("/recipes/{id}.json")
    fun getRecipe(@Path(value = "id") id: String): Call<Recipe>

    @POST("/recipes/{id}.json")
    fun postRecipe(@Path(value = "id") id: String, @Body recipe: Recipe): Call<Recipe>
}