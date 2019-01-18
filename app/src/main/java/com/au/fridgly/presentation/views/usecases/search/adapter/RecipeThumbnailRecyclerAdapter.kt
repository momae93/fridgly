package com.au.fridgly.presentation.views.usecases.search.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.au.fridgly.R
import com.au.fridgly.domain.models.RecipeThumbnail
import com.au.fridgly.presentation.contracts.BaseView
import com.au.fridgly.presentation.views.usecases.BaseActivity
import com.au.fridgly.presentation.views.usecases.recipe.fragment.DialogFragmentRecipe
import com.au.fridgly.presentation.views.usecases.search.viewholder.RecipeThumbnailViewholder
import com.bumptech.glide.Glide

class RecipeThumbnailRecyclerAdapter(val list: List<RecipeThumbnail>, val view: BaseView)
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
        list[position].apply {
            holder.name.text = name
            if (!image.isBlank()){
                Glide.with(view.getViewActivity()).load(image).into(holder.image)
                holder.image.setOnClickListener {
                    (view.getViewActivity() as BaseActivity).showDialog(DialogFragmentRecipe.newInstance(id))
                }
            }
        }
    }
}