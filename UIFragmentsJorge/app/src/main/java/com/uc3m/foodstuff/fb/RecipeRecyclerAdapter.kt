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
            recipeName?.text = recipe.name
            recipeTime?.text = recipe.time.toString()
        }
    }
}