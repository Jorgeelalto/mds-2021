package com.uc3m.foodstuff.app

import android.app.Application
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FoodStuffApp: Application() {

    /* TODO here will be the API
    val webservice by lazy {
        Retrofit.Builder()
                .baseUrl("https://reecipe-weekly-default-rtdb.europe-west1.firebasedatabase.app")
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build().create(ApiService::class.java)
    }*/
}