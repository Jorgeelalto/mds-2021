package com.uc3m.uifragments.app

import android.app.Application
import com.uc3m.uifragments.db.AppDatabase
import com.uc3m.uifragments.db.Recipe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class FoodStuffApp: Application() {

    val database by lazy {
        AppDatabase.getInstance(this)
    }
    // TODO add webservice

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