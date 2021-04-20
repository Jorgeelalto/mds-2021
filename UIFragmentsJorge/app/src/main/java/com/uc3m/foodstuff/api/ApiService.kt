package com.uc3m.foodstuff.api

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search")
    fun searchRecipes(@Query("q") q: String): Call<SearchResponse>
}