package com.au.fridgly.presentation.views.usecases.favorite

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.au.fridgly.R
import com.au.fridgly.domain.models.RecipeThumbnail
import com.au.fridgly.presentation.contracts.favorite.IFavoriteContract
import com.au.fridgly.presentation.presenters.usecases.favorite.FavoritePresenter
import com.au.fridgly.presentation.presenters.usecases.search.SearchResultPresenter
import com.au.fridgly.presentation.views.usecases.MainActivity
import com.au.fridgly.presentation.views.usecases.search.adapter.RecipeThumbnailGridRecyclerAdapter
import com.au.fridgly.presentation.views.usecases.search.adapter.RecipeThumbnailRecyclerAdapter
import javax.inject.Inject

class FragmentFavorite : Fragment(), IFavoriteContract.View {

    private var listener: OnFragmentInteractionListener? = null

    @BindView(R.id.fragment_favorite_recyclerView_favorite)
    lateinit var favoriteRecyclerView: RecyclerView

    @BindView(R.id.fragment_favorite_recyclerView_recently)
    lateinit var historicalRecyclerView: RecyclerView

    private lateinit var presenter: FavoritePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        ButterKnife.bind(this, view)
        (activity as MainActivity).getComponent().inject(this)

        favoriteRecyclerView.layoutManager = GridLayoutManager(context, 2)
        favoriteRecyclerView.setHasFixedSize(true)
        historicalRecyclerView.layoutManager = GridLayoutManager(context, 1, GridLayout.HORIZONTAL, false)
        historicalRecyclerView.setHasFixedSize(true)

        this.presenter.getFavoriteRecipes()
        this.presenter.getRecentRecipes()

        return view
    }

    //region Inject Fields

    @Inject
    fun setPresenter(presenter: FavoritePresenter){
        this.presenter = presenter
        this.presenter.setView(this)
    }

    //endregion


    override fun updateFavoriteRecipes(list: List<RecipeThumbnail>) {
        favoriteRecyclerView.adapter = RecipeThumbnailGridRecyclerAdapter(list, this)
    }

    override fun updateRecentRecipes(list: List<RecipeThumbnail>) {
        historicalRecyclerView.adapter = RecipeThumbnailRecyclerAdapter(list, this)
    }

    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun getViewActivity(): Activity {
        return activity!!
    }

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

    interface OnFragmentInteractionListener
}
