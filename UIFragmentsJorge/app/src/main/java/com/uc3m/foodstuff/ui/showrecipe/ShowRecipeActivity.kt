package com.uc3m.foodstuff.ui.showrecipe

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.uc3m.foodstuff.R
import com.uc3m.foodstuff.fb.Recipe

class ShowRecipeActivity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_recipe)
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
        findViewById<CheckBox>(R.id.gluten_freeRecipe).isChecked = recipe.gluten_free
        findViewById<CheckBox>(R.id.cheapRecipe).isChecked = recipe.cheap
        findViewById<CheckBox>(R.id.dairy_freeRecipe).isChecked = recipe.dairy_free
        findViewById<TextView>(R.id.RecipeTime).text = recipe.timeToString(recipe.time)
        findViewById<TextView>(R.id.IngredientsRecipe).text = recipe.ingredients
        findViewById<TextView>(R.id.RecipeSteps).text = recipe.instructions

        // Set the image
        if (recipe.image.isNotBlank()) {
            val decodedByte = Base64.decode(recipe.image, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.size)
            Log.d("ShowRecipeActivity", recipe.image.substring(IntRange(0, 12)))
            findViewById<ImageView>(R.id.RecipeImage).setImageBitmap(bitmap)
        }
    }
}