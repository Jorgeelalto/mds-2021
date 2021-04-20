package com.uc3m.foodstuff

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
//import com.uc3m.foodstuff.api.ApiClient
import com.uc3m.foodstuff.api.Recipe
import com.uc3m.foodstuff.api.RecipesAdapter
import com.uc3m.foodstuff.ui.newrecipe.NewRecipeActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

//    private var recipeList: MutableList<Recipe> = mutableListOf<Recipe>()
//    private var recipesAdapter: RecipesAdapter? = null
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //For the Recipe API
        var rf : Retrofit = Retrofit.Builder()
            .baseUrl(RetrofitInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        //define API
        var API:RetrofitInterface = rf.create(RetrofitInterface::class.java)
        var call = API.posts

        call?.enqueue(object : Callback<List<recipes?>?>{
            override fun onFailure(call: Call<List<recipes?>?>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<List<recipes?>?>,
                response: Response<List<recipes?>?>
            ) {
                var postList : List<recipes>? = response.body() as List<recipes>
                var post = arrayOfNulls<String>(postList!!.size)
                //getting posts
//                for(i in postList!!.indices)
//                    post[i] = postList!![i]!!.title
//
//                 var adapter = ArrayAdapter<String>(applicationContext,android.R.layout.simple_expandable_list_item_1,post)
                findViewById<ListView>(R.id.listview).adapter = adapter
            }
        })

        ///////////////////////////////////////////////////////

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val fabNewRecipe: FloatingActionButton = findViewById(R.id.fab_new_recipe)
        fabNewRecipe.setOnClickListener {
            startActivity(Intent(this, NewRecipeActivity::class.java))
        }

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_profile, R.id.navigation_weekly, R.id.navigation_search))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // my things
        // TODO FIX!!!!!!!!!!
        /*findViewById<RecyclerView>(R.id.recipe_recyclerview).layoutManager = LinearLayoutManager(this@MainActivity)
        recipesAdapter = RecipesAdapter(this, recipeList)
        findViewById<RecyclerView>(R.id.recipe_recyclerview).adapter = recipesAdapter
        getRecipes()*/
    }

    private fun getRecipes() {

//        var ap: ApiClient = ApiClient()
//        ap.apiService.getAllRecipes().enqueue(object: Callback<List<Recipe>> {
//            override fun onFailure(call: Call<List<Recipe>>, t: Throwable) {
//                println("Error calling the API" + t.localizedMessage)
//            }
//
//            override fun onResponse(call: Call<List<Recipe>>, response: Response<List<Recipe>>) {
//                val recipesResponse = response.body()
//                recipeList.clear()
//                recipesResponse?.let{recipeList.addAll(it)}
//                recipesAdapter?.notifyDataSetChanged()
//
//            }
//        })
    }
}