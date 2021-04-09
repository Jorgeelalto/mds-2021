package com.uc3m.foodstuff.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
data class Recipe (
    @PrimaryKey
    val id: String,
    val name: String,
    val user: String,
    val date: String,
    val desc: String,
    val time: Float
)