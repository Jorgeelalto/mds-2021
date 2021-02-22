package com.uc3m.reecipes.models

class Recipe(val name: String, val description: String) {
    companion object {

        private var lastRecipe = 0

        // TODO Demo purposes only, remove
        fun createRecipeList(numRecipes: Int) : ArrayList<Recipe> {
            val recipes = ArrayList<Recipe>()
            for (i in 0..numRecipes) {
                recipes.add(Recipe(" Recipe " + lastRecipe, "Description"))
                lastRecipe++
            }
            return recipes
        }
    }
}