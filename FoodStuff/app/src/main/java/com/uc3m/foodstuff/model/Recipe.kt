package com.uc3m.foodstuff.model

import androidx.room.PrimaryKey

data class Recipe(
    // TODO this may be removed sometime (key is shared bc of the API db)
    @PrimaryKey(autoGenerate = true)
    val id: String = "0",
    val name: String = "",
    val description: String = "",
    val user: String = ""
    // TODO add more fields
    ) {
    val isValid: Boolean get() = id >= 0 && name.isNotEmpty() && description.isNotEmpty() && user.isNotEmpty()
}