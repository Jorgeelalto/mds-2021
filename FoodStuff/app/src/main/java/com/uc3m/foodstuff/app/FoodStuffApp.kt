package com.uc3m.foodstuff.app

import android.app.Application
import com.google.gson.GsonBuilder
import com.uc3m.foodstuff.model.Recipe
import com.uc3m.foodstuff.model.RecipeDatabase
import com.uc3m.foodstuff.model.RecipeService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class FoodStuffApp: Application() {

    val database by lazy { RecipeDatabase.getInstance(this) }
    val webservice by lazy {
        Retrofit.Builder()
                // TODO put the firebase DB URL here
                .baseUrl("firebaseURL")
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build().create(RecipeService::class.java)
    }
    override fun onCreate() {
        super.onCreate()

        CoroutineScope(Dispatchers.IO + SupervisorJob()).launch {
            database.clearAllTables()
            database.recipeDao.apply {
                this.create(recipe = Recipe(id = "0", name = "Recipe 1"))
                this.create(recipe = Recipe(id = "1", name = "Recipe 2"))
                this.create(recipe = Recipe(id = "2", name = "Recipe 3"))
            }
        }
    }
}