package com.uc3m.foodstuff.ui.newrecipe

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.uc3m.foodstuff.MainActivity
import com.uc3m.foodstuff.R
import com.uc3m.foodstuff.fb.Recipe
import com.uc3m.foodstuff.login.LoggedUserRepo

const val TAG = "NewRecipeActivity"

class NewRecipeActivity : AppCompatActivity() {

    private fun uploadRecipe(context: Context, recipe: Recipe) {
        // Check if the data is correct
        if (!validate(recipe)) {
            return
        }

        val db = Firebase.firestore

        // Create a new user with the password
        val fbrecipe = hashMapOf(
                "user" to recipe.user,
                "name" to recipe.name,
                "description" to recipe.description,
                "non_vegetarian" to recipe.non_vegetarian,
                "vegetarian" to recipe.vegetarian,
                "vegan" to recipe.vegan,
                "gluten_free" to recipe.gluten_free,
                "cheap" to recipe.cheap,
                "dairy_free" to recipe.dairy_free,
                "time" to recipe.time,
                "ingredients" to recipe.ingredients,
                "instructions" to recipe.instructions
        )

        // Add a new document with a generated ID
        db.collection("recipes").add(fbrecipe)
                .addOnSuccessListener { documentReference ->
                    Log.d(com.uc3m.foodstuff.login.TAG, "DocumentSnapshot added with ID: $documentReference")
                    Toast.makeText(context, "Recipe submitted correctly", Toast.LENGTH_SHORT).show()
                    ContextCompat.startActivity(this, Intent(this, MainActivity::class.java), null)
                }
                .addOnFailureListener { e ->
                    Log.w(com.uc3m.foodstuff.login.TAG, "Error adding document", e)
                    Toast.makeText(context, "The recipe could not be submitted", Toast.LENGTH_SHORT).show()
                }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_recipe)
        setSupportActionBar(findViewById(R.id.toolbar))

        val loggedUserRepo by lazy { LoggedUserRepo(this) }

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            val recipe: Recipe = Recipe()

            recipe.user = loggedUserRepo.getLoggedUser()
            recipe.name = findViewById<EditText>(R.id.name_edit).text.toString()
            recipe.description = findViewById<EditText>(R.id.description_edit).text.toString()
            recipe.non_vegetarian = findViewById<RadioButton>(R.id.radio_nonveggie).isEnabled
            recipe.vegetarian = findViewById<RadioButton>(R.id.radio_vegetarian).isEnabled
            recipe.vegan = findViewById<RadioButton>(R.id.radio_vegan).isEnabled
            recipe.gluten_free = findViewById<CheckBox>(R.id.gluten_free).isEnabled
            recipe.dairy_free = findViewById<CheckBox>(R.id.dairy_free).isEnabled
            recipe.cheap = findViewById<CheckBox>(R.id.cheap).isEnabled
            val ttime = findViewById<EditText>(R.id.time_edit).text.toString().toFloatOrNull()
            if (ttime == null) recipe.time = -1.0f
            else recipe.time = ttime
            recipe.ingredients = findViewById<EditText>(R.id.ingredients_edit).text.toString()
            recipe.instructions = findViewById<EditText>(R.id.instructions_edit).text.toString()

            uploadRecipe(this, recipe)
            /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()*/
        }
    }

    private fun validate(recipe: Recipe): Boolean {

        if (recipe.user.isBlank()) {
            Log.d(TAG, "Recipe user is empty")
            Toast.makeText(this, "The user data in the recipe is corrupt", Toast.LENGTH_SHORT).show()
            return false
        }
        if (recipe.name.isBlank()) {
            Log.d(TAG, "Recipe name is empty")
            Toast.makeText(this, "Recipe name is empty. Please specify a name", Toast.LENGTH_SHORT).show()
            return false
        }
        if (recipe.non_vegetarian.not() && recipe.vegetarian.not() && recipe.vegan.not()) {
            Log.d(TAG, "Recipe is not non-vegetarian, vegetarian nor vegan")
            Toast.makeText(this, "Recipe is not non-vegetarian, vegetarian nor vegan. How could this happen?", Toast.LENGTH_SHORT).show()
            return false
        }
        if (recipe.time <= 0.0f) {
            Log.d(TAG, "Recipe has negative or zero time")
            Toast.makeText(this, "Time is invalid. Please specify a correct preparation time (for example, 1.5)", Toast.LENGTH_SHORT).show()
            return false
        }
        if (recipe.ingredients.isBlank()) {
            Log.d(TAG, "Recipe ingredients is empty")
            Toast.makeText(this, "There are no ingredients. You cannot cook nothing!", Toast.LENGTH_SHORT).show()
            return false
        }
        if (recipe.instructions.isBlank()) {
            Log.d(TAG, "Recipe instructions is empty")
            Toast.makeText(this, "You did not specify the preparation instructions", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}
