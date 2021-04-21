package com.uc3m.foodstuff.ui.showrecipe

import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.uc3m.foodstuff.R
import com.uc3m.foodstuff.fb.Recipe

class ShowRecipeActivity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_show_recipe)
        setSupportActionBar(findViewById(R.id.toolbar))

        // Get the recipe from the intent
        val recipe: Recipe = intent.extras?.get("recipe") as Recipe
        findViewById<TextView>(R.id.RecipeName).text = recipe.name
        findViewById<TextView>(R.id.RecipeDescription).text = recipe.description
        when {
            recipe.vegan -> findViewById<TextView>(R.id.VegetarianRecipe).text = "Vegan"
            recipe.vegetarian -> findViewById<TextView>(R.id.VegetarianRecipe).text = "Vegetarian"
            else -> findViewById<TextView>(R.id.VegetarianRecipe).text = "Non-vegetarian"
        }
        findViewById<CheckBox>(R.id.gluten_freeRecipe).isEnabled = recipe.gluten_free
        findViewById<CheckBox>(R.id.cheapRecipe).isEnabled = recipe.cheap
        findViewById<CheckBox>(R.id.dairy_freeRecipe).isEnabled = recipe.dairy_free
        findViewById<TextView>(R.id.RecipeTime).text = recipe.timeToString(recipe.time)
        findViewById<TextView>(R.id.IngredientsRecipe).text = recipe.ingredients
        findViewById<TextView>(R.id.RecipeSteps).text = recipe.instructions
    }
}