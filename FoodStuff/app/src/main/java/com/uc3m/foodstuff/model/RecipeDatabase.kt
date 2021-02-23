package com.uc3m.foodstuff.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Recipe::class], version = 1, exportSchema = false)
abstract class RecipeDatabase: RoomDatabase() {
    abstract val recipeDao: RecipeDao

    companion object {

        @Volatile
        private var INSTANCE: RecipeDatabase? = null

        fun getInstance(context: Context): RecipeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecipeDatabase::class.java,
                    "foodstuff_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}