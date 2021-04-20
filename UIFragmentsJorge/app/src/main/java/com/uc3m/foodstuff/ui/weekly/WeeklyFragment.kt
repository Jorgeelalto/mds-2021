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
import com.uc3m.foodstuff.R
import com.uc3m.foodstuff.api.ApiRecipe
import com.uc3m.foodstuff.api.ApiRecipeRecyclerAdapter
import com.uc3m.foodstuff.api.ApiService
import com.uc3m.foodstuff.api.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://forkify-api.herokuapp.com/api/"
const val TAG = "WeeklyFragment"

class WeeklyFragment : Fragment() {

    private lateinit var weeklyViewModel: WeeklyViewModel
    private var recipeList: ArrayList<ApiRecipe> = arrayListOf()


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

        // ----------
        // API things
        // ----------

        val recyclerView = root.findViewById<RecyclerView>(R.id.weekly_recipe_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(context)

        recipeList = arrayListOf()
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create(ApiService::class.java)
        val call = service.searchRecipes("pizza")
        Log.d(TAG, call.toString())
        call.enqueue(object: Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                if (response.code() == 200) {
                    val search = response.body()
                    if (search != null) {
                        for (r in search.recipes) {
                            recipeList.add(r)
                        }
                        val adapter = context?.let { ApiRecipeRecyclerAdapter(it, recipeList!!) }
                        recyclerView.adapter = adapter
                    }
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.d(TAG, "do not work")
                Log.d(TAG, call.request().url.toString())
                Log.d(TAG, t.localizedMessage)
                Log.d(TAG, t.stackTrace.toString())
            }
        })

        return root
    }
}