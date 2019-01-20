package com.au.fridgly.presentation.views.usecases.recipe.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.au.fridgly.R

class RecipeIngredientViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var picture: ImageView = itemView.findViewById(R.id.item_recipe_ingredient_imageView_photo)
}