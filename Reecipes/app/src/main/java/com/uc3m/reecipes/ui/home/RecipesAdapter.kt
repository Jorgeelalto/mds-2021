package com.uc3m.reecipes.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uc3m.reecipes.R
import com.uc3m.reecipes.models.Recipe

class RecipesAdapter(private val mRecipes: List<Recipe>) : RecyclerView.Adapter<RecipesAdapter.ViewHolder>() {

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val nameTextView = itemView.findViewById<TextView>(R.id.recipe_name)
        // TODO Add description and other fields
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val recipeView = inflater.inflate(R.layout.item_recipe_short, parent, false)
        return ViewHolder(recipeView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe: Recipe = mRecipes[position]
        val textView = holder.nameTextView
        textView.text = recipe.name
        // TODO Add description and the rest of the fields
    }

    override fun getItemCount(): Int {
        return mRecipes.size
    }
}