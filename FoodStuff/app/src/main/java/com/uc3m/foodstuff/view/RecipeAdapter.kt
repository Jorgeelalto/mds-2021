package com.uc3m.foodstuff.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uc3m.foodstuff.viewmodels.RecipeListViewModel

class RecipeAdapter(private val viewModel: RecipeListViewModel): RecyclerView.Adapter<RecipeListAdapter.RecipeItemViewHolder>() {

    class RecipeItemViewHolder(private val viewModel: RecipeListViewModel, binding: ItemRecipeBinding): RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        val recipeName = binding.recipeName
        init {
            binding.root.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            viewModel.onClickItem(layoutPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeItemViewHolder =
            RecipeItemViewHolder(viewModel, ItemRecipeBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: RecipeItemViewHolder, position: Int) {
        val recipe = viewModel.getItem(position)
        holder.recipeName.text = "Wonderful ${recipe.name}"
    }

    override fun getItemCount(): Int = viewModel.numberOfItems
}