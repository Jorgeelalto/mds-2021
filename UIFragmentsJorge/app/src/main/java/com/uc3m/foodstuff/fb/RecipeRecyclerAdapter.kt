package com.uc3m.foodstuff.fb

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
        private val recipeImg = view?.findViewById<ImageView>(R.id.recipe_weekly_img)

        fun bind(recipe: Recipe, context: Context) {

            // Set the recipe name and time
            recipeName?.text = recipe.name
            recipeTime?.text = recipe.timeToString(recipe.time)

            // Set the image
            if (recipe.image.isNotBlank()) {
                val decodedByte = Base64.decode(recipe.image, Base64.DEFAULT)
                val bitmap = BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.size)
                try {
                    Log.d("RecipeRecyclerAdapter", recipe.image.substring(IntRange(0, 12)))
                } catch (e: Exception) {
                    Log.d("RecipeRecyclerAdapter", "No image found")
                }
                recipeImg?.setImageBitmap(bitmap)
            }
        }
    }
}
