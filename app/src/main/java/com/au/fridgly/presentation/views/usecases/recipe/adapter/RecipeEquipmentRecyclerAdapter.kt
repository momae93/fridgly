package com.au.fridgly.presentation.views.usecases.recipe.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.au.fridgly.R
import com.au.fridgly.presentation.contracts.BaseView
import com.au.fridgly.presentation.models.recipeDetails.EquipmentUI
import com.au.fridgly.presentation.views.usecases.recipe.viewholder.RecipeEquipmentViewHolder
import com.bumptech.glide.Glide

class RecipeEquipmentRecyclerAdapter(val list: List<EquipmentUI>, val view: BaseView)
    : RecyclerView.Adapter<RecipeEquipmentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeEquipmentViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_recipe_equipments, parent, false)
        return RecipeEquipmentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecipeEquipmentViewHolder, position: Int) {
        list[position].apply {
            val url = "https://spoonacular.com/cdn/equipment_100x100/$image"
            if (!image.isNullOrBlank()){
                Glide.with(view.getViewActivity()).load(url).into(holder.picture)
            }
            else{
                val drawable = view.getViewActivity().resources.getDrawable(R.drawable.al_missing)
                holder.picture.setImageDrawable(drawable)
            }
            holder.picture.setOnClickListener { view.showToast(name) }
        }
    }
}