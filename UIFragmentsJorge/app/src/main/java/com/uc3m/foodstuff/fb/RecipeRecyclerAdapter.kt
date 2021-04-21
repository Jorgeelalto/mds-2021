package com.uc3m.foodstuff.fb

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uc3m.foodstuff.R

class RecipeRecyclerAdapter(
        val context: Context,
        private val RecipeList: ArrayList<Recipe>)
    : RecyclerView.Adapter<RecipeRecyclerAdapter.Holder>() {

    override fun onBindViewHolder(holder: RecipeRecyclerAdapter.Holder, position: Int) {
        holder.bind(RecipeList[position], context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeRecyclerAdapter.Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_profile_recipe, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return RecipeList.size
    }

    inner class Holder(view: View?) : RecyclerView.ViewHolder(view!!) {
        private val recipeName = view?.findViewById<TextView>(R.id.recipe_name)
        private val recipeTime = view?.findViewById<TextView>(R.id.recipe_time)

        fun bind(recipe: Recipe, context: Context) {
            // Set the recipe name
            recipeName?.text = recipe.name
            // Set the recipe time. We can do nicer things than putting just the number:
            var hours = recipe.time.toInt()
            var min = recipe.time
            while (min > 1) min -= 1
            min *= 60
            var minutes = min.toInt()
            if (minutes == 60) {
                minutes = 0
                hours += 1
            }
            // Print the numbers into a nice string
            if (hours == 0) {
                recipeTime?.text = "$minutes minutes"
            } else if (minutes == 0) {
                if (hours == 1) recipeTime?.text = "$hours hour"
                else recipeTime?.text = "$hours hours"
            } else {
                if (hours == 1) recipeTime?.text = "$hours hour and $minutes minutes"
                else recipeTime?.text = "$hours hours and $minutes minutes"
            }
        }
    }
}
