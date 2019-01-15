package com.au.fridgly.presentation.views.usecases.search.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife

import com.au.fridgly.R
import com.au.fridgly.domain.models.RecipeThumbnail
import com.au.fridgly.presentation.contracts.search.ISearchContract
import com.au.fridgly.presentation.presenters.usecases.search.SearchPresenter
import com.au.fridgly.presentation.views.usecases.MainActivity
import com.au.fridgly.presentation.views.usecases.search.adapter.RecipeThumbnailRecyclerAdapter
import com.bumptech.glide.Glide
import javax.inject.Inject


class FragmentSearch : Fragment(), ISearchContract.View {

    @BindView(R.id.fragment_search_searchView_search)
    lateinit var searchView: SearchView

    private var listener: OnFragmentInteractionListener? = null
    private lateinit var presenter: SearchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        ButterKnife.bind(this, view)

        (activity as MainActivity).getComponent().inject(this)
        (activity as MainActivity).replaceFragment(R.id.fragment_search_frameLayout_container, FragmentSearchRandom())

        searchView.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }

            @SuppressLint("CheckResult")
            override fun onQueryTextSubmit(query: String): Boolean {
                if (!query.isBlank())
                {
                    val message =  "Searching..."
                    showToast(message)
                }
                else
                {
                    val message =  "Oups, avez-vous oublié de saisir un produit ?"
                    showToast(message)
                }
                return true
            }
        })

        return view
    }

    //region Inject Fields

    @Inject
    fun setPresenter(presenter: SearchPresenter){
        this.presenter = presenter
        this.presenter.setView(this)
    }

    //endregion

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun getViewActivity(): Activity {
        return activity!!
    }

    interface OnFragmentInteractionListener
}
