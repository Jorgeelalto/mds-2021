package com.uc3m.foodstuff.api

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uc3m.foodstuff.R
import java.net.URL

const val TAG = "ApiRecipeRecyclerAdapt"

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
        private val recipeImg = view?.findViewById<ImageView>(R.id.recipe_img)

        fun bind(recipe: ApiRecipe, context: Context) {
            // TODO make nice
            recipeName?.text = recipe.title
            recipeTime?.text = recipe.publisher

            Log.d(TAG, recipe.image_url)
            try {
                val bmp = BitmapFactory.decodeStream(URL(recipe.image_url).openStream())
                recipeImg?.setImageBitmap(bmp)
            } catch (t: Throwable) {
                if (t.localizedMessage != null) Log.d(TAG, t.localizedMessage)
                Log.d(TAG, t.stackTrace.toString())
            }
        }
    }
}