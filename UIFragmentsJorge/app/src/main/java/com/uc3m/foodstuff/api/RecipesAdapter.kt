package com.uc3m.foodstuff.api

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uc3m.foodstuff.R

class RecipesAdapter(private val context: Context, private var list: MutableList<Recipe>): RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(var view: View): RecyclerView.ViewHolder(view) {
        // TODO add the rest of the Recipe class fields
        var name: TextView? = null
        var time: TextView? = null

        init {
            name = view.findViewById(R.id.recipe_name)
            time = view.findViewById(R.id.recipe_time)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.fragment_view_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = list.get(position)
        holder.name?.text = recipe.name
        holder.time?.text = recipe.time.toString()
        // TODO add the rest of the fields
    }
}