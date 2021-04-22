package com.uc3m.foodstuff.fb

import java.io.Serializable

data class Recipe (
        var user: String = "",
        var name: String = "",
        var description: String = "",
        var non_vegetarian: Boolean = true,
        var vegetarian: Boolean = false,
        var vegan: Boolean = false,
        var gluten_free: Boolean = false,
        var cheap: Boolean = false,
        var dairy_free: Boolean = false,
        var time: Float = 0.0f,
        var ingredients: String = "",
        var instructions: String = "",
        var image: String = ""
) : Serializable {

    fun timeToString(time: Float): String {
        // Set the recipe time. We can do nicer things than putting just the number:
        var hours = time.toInt()
        var min = time
        while (min > 1) min -= 1
        min *= 60
        var minutes = min.toInt()
        if (minutes == 60) {
            minutes = 0
            hours += 1
        }
        // Print the numbers into a nice string
        return if (hours == 0) {
            "$minutes minutes"
        } else if (minutes == 0) {
            if (hours == 1) "$hours hour"
            else "$hours hours"
        } else {
            if (hours == 1) "$hours hour and $minutes minutes"
            else "$hours hours and $minutes minutes"
        }
    }
}
