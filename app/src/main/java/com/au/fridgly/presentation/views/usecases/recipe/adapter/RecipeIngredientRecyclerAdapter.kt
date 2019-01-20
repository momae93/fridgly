package com.au.fridgly.presentation.views.usecases.recipe.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.au.fridgly.R
import com.au.fridgly.presentation.contracts.BaseView
import com.au.fridgly.presentation.models.recipeDetails.IngredientUI
import com.au.fridgly.presentation.views.usecases.recipe.viewholder.RecipeIngredientViewHolder
import com.bumptech.glide.Glide

class RecipeIngredientRecyclerAdapter(val list: List<IngredientUI>, val view: BaseView)
    : RecyclerView.Adapter<RecipeIngredientViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeIngredientViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_recipe_ingredients, parent, false)
        return RecipeIngredientViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecipeIngredientViewHolder, position: Int) {
        list[position].apply {
            val url = "https://spoonacular.com/cdn/ingredients_100x100/$image"
            if (!image.isBlank()){
                Glide.with(view.getViewActivity()).load(url).into(holder.picture)
            }
            holder.picture.setOnClickListener { view.showToast(name) }
        }
    }
}