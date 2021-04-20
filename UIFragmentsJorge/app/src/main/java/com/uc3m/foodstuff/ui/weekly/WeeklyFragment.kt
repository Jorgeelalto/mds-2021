package com.uc3m.foodstuff.ui.weekly

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.uc3m.foodstuff.R
import com.uc3m.foodstuff.api.ApiRecipe
import com.uc3m.foodstuff.api.ApiRecipeRecyclerAdapter
import com.uc3m.foodstuff.api.ApiService
import com.uc3m.foodstuff.api.SearchResponse
import com.uc3m.foodstuff.fb.Recipe
import com.uc3m.foodstuff.fb.RecipeRecyclerAdapter
import com.uc3m.foodstuff.login.LoggedUserRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://forkify-api.herokuapp.com/api/"
const val TAG = "WeeklyFragment"


class WeeklyFragment : Fragment() {

    private lateinit var weeklyViewModel: WeeklyViewModel
    private var recipeList: ArrayList<Recipe> = arrayListOf()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        weeklyViewModel =
                ViewModelProvider(this).get(WeeklyViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_weekly, container, false)
        val textView: TextView = root.findViewById(R.id.text_hereweekly)
        weeklyViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        // ---------------------------
        // Firebase recyclerview thing
        // ---------------------------

        // Get the recyclerView element and set its layoutManager
        val recyclerView = root.findViewById<RecyclerView>(R.id.weekly_recipe_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Attach a lambda to the firestore query so that the recipe list is filled each time
        // the query results are updated
        val documentReference = firestore.collection("recipes")

        documentReference.addSnapshotListener { value, _ ->
            recipeList = arrayListOf()
            if (value != null) {
                for (d in value.documents) {
                    val recipe = d.toObject(Recipe::class.java)
                    if (recipe != null) {
                        recipeList!!.add(recipe)
                    }
                }

                // Update the recyclerview with a new adapter with the recipes
                val adapter = context?.let { RecipeRecyclerAdapter(it, recipeList!!) }
                recyclerView.adapter = adapter
            }
        }

        return root
    }
}