package com.uc3m.foodstuff.api

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.uc3m.foodstuff.R
import com.uc3m.foodstuff.ui.webrecipe.WebRecipeActivity
import java.io.Serializable

const val TAG = "ApiRecipeRecyclerAdapt"

class ApiRecipeRecyclerAdapter(
        val context: Context,
        private val RecipeList: ArrayList<ApiRecipe>)
    : RecyclerView.Adapter<ApiRecipeRecyclerAdapter.Holder>() {

    override fun onBindViewHolder(holder: ApiRecipeRecyclerAdapter.Holder, position: Int) {
        holder.itemView.setOnClickListener {
            // Open show recipe activity
            val recipe = RecipeList[position]
            val intent = Intent(context, WebRecipeActivity::class.java)
            intent.putExtra("recipeUrl", recipe.source_url as Serializable)
            ContextCompat.startActivity(context, intent, null)
        }
        holder.bind(RecipeList[position], context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApiRecipeRecyclerAdapter.Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_search_recipe, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return RecipeList.size
    }

    inner class Holder(view: View?) : RecyclerView.ViewHolder(view!!) {
        private val recipeName = view?.findViewById<TextView>(R.id.recipe_name)
        private val recipeTime = view?.findViewById<TextView>(R.id.recipe_time)
        private val recipeImg =  view?.findViewById<ImageView>(R.id.recipe_img)

        fun bind(recipe: ApiRecipe, context: Context) {
            recipeName?.text = recipe.title
            recipeTime?.text = recipe.publisher

            try {
                var imageUrl = recipe.image_url
                // Force HTTPS
                if ("https" !in imageUrl) imageUrl = imageUrl.replace("http", "https")
                Log.d(TAG, imageUrl)
                Picasso.get().load(imageUrl).into(recipeImg)
            } catch (e: Exception /*t: Throwable*/) {
                Log.d(TAG, "Holia: " + recipeImg.toString())
                if (e.localizedMessage != null) Log.d(TAG, e.localizedMessage.toString())
                Log.d(TAG, e.cause.toString())
                e.message?.let { Log.d(TAG, it) }
                Log.d(TAG, e.stackTrace.toString())
            }
        }
    }
}