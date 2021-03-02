package com.uc3m.foodstuff.viewmodels

import androidx.lifecycle.*
import com.uc3m.foodstuff.model.Recipe
import com.uc3m.foodstuff.model.RecipeDatabase
import com.uc3m.foodstuff.model.RecipeService
import com.uc3m.foodstuff.util.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.await

class RecipeListViewModel(private val database: RecipeDatabase, private val webservice: RecipeService): ViewModel() {
    private val _navigate: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val navigate: LiveData<Event<Boolean>> = _navigate
    private var recipeList = listOf<Recipe>()
    data class Item(val id: Int) {
        val name: String = ""
        // TODO add the rest of the fields
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            recipeList = database.recipeDao.fetchRecipes()
        }
    }

    val numberOfItems: Int
        get() = recipeList.count()

    fun addButtonClicked() {
        _navigate.value = Event(true)
    }

    fun getItem(n: Int) = Item(id = recipeList[n].id)

    fun onClickItem(n: Int) {
        println("Item $n clicked")
        viewModelScope.launch(Dispatchers.IO) {
            val recipe = webservice.getRecipe(n).await()
            println("Recipe: ${recipe.name}")
        }
    }
}

class RecipeListViewModelFactory(private val database: RecipeDatabase, private val webservice: RecipeService): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipeListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RecipeListViewModel(database, webservice) as T
        }
        throw IllegalArgumentException("Unknown VIewModel class")
    }
}