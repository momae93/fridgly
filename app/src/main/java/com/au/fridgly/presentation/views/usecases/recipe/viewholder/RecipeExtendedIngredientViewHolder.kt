package com.au.fridgly.presentation.views.usecases.recipe.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.au.fridgly.R

class RecipeExtendedIngredientViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var picture: ImageView = itemView.findViewById(R.id.item_recipe_ingredient_extended_imageView_photo)
    var name: TextView = itemView.findViewById(R.id.item_recipe_ingredient_extended_textView_ingredient)
    var quantity: TextView = itemView.findViewById(R.id.item_recipe_ingredient_extended_textView_quantity)
    var unit: TextView = itemView.findViewById(R.id.item_recipe_ingredient_extended_textView_unit)
}