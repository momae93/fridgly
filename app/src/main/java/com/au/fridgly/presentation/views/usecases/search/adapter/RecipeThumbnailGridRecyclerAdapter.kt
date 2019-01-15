package com.au.fridgly.presentation.views.usecases.search.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.au.fridgly.R
import com.au.fridgly.domain.models.RecipeThumbnail
import com.au.fridgly.presentation.contracts.BaseView
import com.au.fridgly.presentation.views.usecases.search.viewholder.RecipeThumbnailGridViewHolder
import com.bumptech.glide.Glide

class RecipeThumbnailGridRecyclerAdapter(val list: List<RecipeThumbnail>, val view: BaseView)
    : RecyclerView.Adapter<RecipeThumbnailGridViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeThumbnailGridViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_recipe_thumbnail_grid, parent, false)
        return RecipeThumbnailGridViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecipeThumbnailGridViewHolder, position: Int) {
        holder.name.text = list[position].name
        val url = list[position].image
        if (!url.isBlank()){
            Glide.with(view.getViewActivity()).load(url).into(holder.image)
        }
    }
}