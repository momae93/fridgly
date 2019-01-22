package com.au.fridgly.presentation.views.usecases.scan.adapter

import android.support.v4.app.DialogFragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.au.fridgly.R
import com.au.fridgly.domain.models.EAliment
import com.au.fridgly.presentation.contracts.BaseView
import com.au.fridgly.presentation.contracts.scan.IScanResultContract
import com.au.fridgly.presentation.views.usecases.MainActivity
import com.au.fridgly.presentation.views.usecases.scan.viewholder.AlimentViewHolder
import com.au.fridgly.presentation.views.usecases.search.fragment.FragmentSearchResults

class AlimentRecyclerAdapter(val list: List<EAliment>, val view: BaseView)
    : RecyclerView.Adapter<AlimentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlimentViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_aliment_thumbnail, parent, false)
        return AlimentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AlimentViewHolder, position: Int) {
        list[position].apply {
            holder.name.text = description
            val drawable = view.getViewActivity().resources.getDrawable(image)
            holder.picture.setImageDrawable(drawable)
            holder.picture.setOnClickListener {
                (this@AlimentRecyclerAdapter.view as IScanResultContract.View).dismiss()
                val fragmentSearchResults = FragmentSearchResults.newInstance(alimentName)
                (view.getViewActivity() as MainActivity).replaceFragment(R.id.fragment_search_frameLayout_container, fragmentSearchResults)
            }
        }
    }
}