package com.au.fridgly.presentation.views.usecases.search.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.au.fridgly.R
import com.au.fridgly.domain.models.RecipeThumbnail
import com.au.fridgly.presentation.contracts.search.ISearchContract
import com.au.fridgly.presentation.views.usecases.search.viewholder.RecipeThumbnailViewholder
import com.bumptech.glide.Glide

class RecipeThumbnailRecyclerAdapter(val list: List<RecipeThumbnail>, val view: ISearchContract.View)
    : RecyclerView.Adapter<RecipeThumbnailViewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeThumbnailViewholder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_recipe_thumbnail, parent, false)
        return RecipeThumbnailViewholder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecipeThumbnailViewholder, position: Int) {
        holder.name.text = list[position].name
        val url = list[position].image
        if (!url.isBlank()){
            Glide.with(view.getViewActivity()).load(url).into(holder.image)
        }
    }
}