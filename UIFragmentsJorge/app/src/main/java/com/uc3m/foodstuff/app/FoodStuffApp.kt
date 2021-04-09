package com.uc3m.foodstuff.app

import android.app.Application
import com.google.gson.GsonBuilder
import com.uc3m.foodstuff.api.ApiService
import com.uc3m.foodstuff.db.AppDatabase
import com.uc3m.foodstuff.db.Recipe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FoodStuffApp: Application() {

    val database by lazy {
        AppDatabase.getInstance(this)
    }
    val webservice by lazy {
        Retrofit.Builder()
                .baseUrl("https://reecipe-weekly-default-rtdb.europe-west1.firebasedatabase.app")
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build().create(ApiService::class.java)
    }

    override fun onCreate() {
        super.onCreate()

        CoroutineScope(Dispatchers.IO + SupervisorJob()).launch {
            // TODO remove
            database.clearAllTables()
            database.recipeDao().apply {
                this.insert(recipe = Recipe(
                        id = "d9sd8sd",
                        name = "Yummy Void",
                        user = "manya",
                        date = "today", // TODO this should be a nice format, parseable
                        desc = "A recipe made of void",
                        time = 1.0f
                ))
            }
        }
    }
}