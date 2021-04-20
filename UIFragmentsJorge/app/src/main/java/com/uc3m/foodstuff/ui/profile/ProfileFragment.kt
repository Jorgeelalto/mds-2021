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

    val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    var recipeList: ArrayList<Recipe>? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        val textView: TextView = root.findViewById(R.id.text_username)
        textView.text = "Hello, ${loggedUserRepo.getLoggedUser()}."
        /*profileViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/

        // Firebase recyclerview thing
        var recyclerView = root.findViewById<RecyclerView>(R.id.recipe_recyclerview)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)

        recipeList = arrayListOf<Recipe>()
        val documentReference = firestore.collection("recipes")
        // TODO put username
        val query = documentReference.whereEqualTo("user", "jorge")
        query.addSnapshotListener { value, error ->
            if (value != null) {
                for (d in value.documents) {
                    val recipe = d.toObject(Recipe::class.java)
                    if (recipe != null) {
                        recipeList!!.add(recipe)
                    }
                }

                val adapter = context?.let { RecipeRecyclerAdapter(it, recipeList!!) }
                recyclerView.adapter = adapter
            }
        }

        return root
    }
}