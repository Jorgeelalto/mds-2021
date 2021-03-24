package com.uc3m.uifragments.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "recipe")
data class Recipe (
    @PrimaryKey
    val id: String,
    val user: String,
    val date: String,
    val desc: String,
    val time: Float
)