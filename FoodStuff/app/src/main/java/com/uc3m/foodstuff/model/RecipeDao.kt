package com.uc3m.foodstuff.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface RecipeDao {
    @Insert
    suspend fun create(recipe: Recipe)
    @Query("SELECT * FROM recipes WHERE id = :id")
    suspend fun get(id: Int): Recipe?
    @Query("SELECT * FROM recipes")
    suspend fun fetchTeas(): List<Recipe>
    @Update
    suspend fun update(tea: Recipe)
    @Query("DELETE FROM recipes WHERE id = :id")
    suspend fun delete(id: Int)
}