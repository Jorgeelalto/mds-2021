package com.uc3m.foodstuff.fb

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.uc3m.foodstuff.R
import com.uc3m.foodstuff.ui.showrecipe.ShowRecipeActivity
import java.io.Serializable

class RecipeRecyclerAdapter(
        val context: Context,
        private val RecipeList: ArrayList<Recipe>)
    : RecyclerView.Adapter<RecipeRecyclerAdapter.Holder>() {

    override fun onBindViewHolder(holder: RecipeRecyclerAdapter.Holder, position: Int) {
        holder.itemView.setOnClickListener {
            // Open show recipe activity
            val recipe = RecipeList[position]
            val intent = Intent(context, ShowRecipeActivity::class.java)
            intent.putExtra("recipe", recipe as Serializable)
            ContextCompat.startActivity(context, intent, null)
        }
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
            recipeTime?.text = recipe.timeToString(recipe.time)
        }
    }
}
