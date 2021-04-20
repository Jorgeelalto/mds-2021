package com.uc3m.foodstuff

import retrofit2.Call
import retrofit2.http.GET


interface RetrofitInterface {

    @get:GET ( "posts")
    val posts : Call<List<recipes?>?>?

    companion object{
        const val BASE_URL = "https://jsonplaceholder.typicode.com"
    }
}