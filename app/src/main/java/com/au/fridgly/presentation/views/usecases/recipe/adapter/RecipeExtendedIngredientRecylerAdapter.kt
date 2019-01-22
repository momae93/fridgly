package com.au.fridgly.presentation.views.usecases.recipe.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.au.fridgly.R
import com.au.fridgly.domain.models.recipeDetails.ExtendedIngredient
import com.au.fridgly.presentation.contracts.BaseView
import com.au.fridgly.presentation.views.usecases.recipe.viewholder.RecipeExtendedIngredientViewHolder
import com.bumptech.glide.Glide

class RecipeExtendedIngredientRecylerAdapter(val list: List<ExtendedIngredient>, val view: BaseView)
    : RecyclerView.Adapter<RecipeExtendedIngredientViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeExtendedIngredientViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_recipe_ingredients_extended, parent, false)
        return RecipeExtendedIngredientViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecipeExtendedIngredientViewHolder, position: Int) {
        list[position].apply {
            holder.name.text = name
            holder.quantity.text = amount.toString()
            holder.unit.text = unit
            val url = "https://spoonacular.com/cdn/ingredients_100x100/$image"
            if (!image.isNullOrBlank()){
                Glide.with(view.getViewActivity()).load(url).into(holder.picture)
            }
            else{
                val drawable = view.getViewActivity().resources.getDrawable(R.drawable.al_missing)
                holder.picture.setImageDrawable(drawable)
            }
        }
    }
}