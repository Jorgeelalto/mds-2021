package com.uc3m.foodstuff.api

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uc3m.foodstuff.R

class ApiRecipeRecyclerAdapter(
        val context: Context,
        private val RecipeList: ArrayList<ApiRecipe>)
    : RecyclerView.Adapter<ApiRecipeRecyclerAdapter.Holder>() {

    override fun onBindViewHolder(holder: ApiRecipeRecyclerAdapter.Holder, position: Int) {
        holder.bind(RecipeList[position], context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApiRecipeRecyclerAdapter.Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_weekly_recipe, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return RecipeList.size
    }

    inner class Holder(view: View?) : RecyclerView.ViewHolder(view!!) {
        private val recipeName = view?.findViewById<TextView>(R.id.recipe_name)
        private val recipeTime = view?.findViewById<TextView>(R.id.recipe_time)

        fun bind(recipe: ApiRecipe, context: Context) {
            // TODO make nice
            recipeName?.text = recipe.title
            recipeTime?.text = recipe.publisher
        }
    }
}