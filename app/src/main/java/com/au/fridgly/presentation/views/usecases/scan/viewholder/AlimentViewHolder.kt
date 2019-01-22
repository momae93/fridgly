package com.au.fridgly.presentation.views.usecases.scan.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.au.fridgly.R

class AlimentViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var picture: ImageView = itemView.findViewById(R.id.item_aliment_thumbnail_imageView_photo)
    var name: TextView = itemView.findViewById(R.id.item_aliment_thumbnail_textView_name)
}