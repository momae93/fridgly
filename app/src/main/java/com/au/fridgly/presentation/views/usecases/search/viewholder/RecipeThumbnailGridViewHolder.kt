package com.au.fridgly.presentation.views.usecases.search.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.au.fridgly.R

class RecipeThumbnailGridViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var image: ImageView = itemView.findViewById(R.id.item_recipe_thumbnail_grid_imageView_photo)
    var name: TextView = itemView.findViewById(R.id.item_recipe_thumbnail_grid_textView_name)
}