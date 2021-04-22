package com.uc3m.foodstuff.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*
import com.uc3m.foodstuff.R
import com.uc3m.foodstuff.fb.Recipe
import com.uc3m.foodstuff.fb.RecipeRecyclerAdapter
import com.uc3m.foodstuff.login.LoggedUserRepo

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private val loggedUserRepo by lazy { LoggedUserRepo(requireContext()) }

    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var recipeList: ArrayList<Recipe> = arrayListOf()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        val textView: TextView = root.findViewById(R.id.text_username)
        textView.text = "Hello, ${loggedUserRepo.getLoggedUser()}."

        // ---------------------------
        // Firebase recyclerview thing
        // ---------------------------

        // Get the recyclerView element and set its layoutManager
        val recyclerView = root.findViewById<RecyclerView>(R.id.recipe_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Attach a lambda to the firestore query so that the recipe list is filled each time
        // the query results are updated
        val documentReference = firestore.collection("recipes")
        val loggedUserRepo: LoggedUserRepo? = context?.let { LoggedUserRepo(it) }
        val query = documentReference.whereEqualTo("user", loggedUserRepo?.getLoggedUser())

        query.addSnapshotListener { value, _ ->
            recipeList = arrayListOf()
            if (value != null) {
                for (d in value.documents) {
                    val recipe = d.toObject(Recipe::class.java)
                    if (recipe != null) {
                        recipeList.add(recipe)
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