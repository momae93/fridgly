package com.au.fridgly.presentation.views.usecases.search.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.au.fridgly.R
import com.au.fridgly.domain.models.RecipeThumbnail
import com.au.fridgly.presentation.contracts.search.ISearchResultContract
import com.au.fridgly.presentation.presenters.usecases.search.SearchResultPresenter
import com.au.fridgly.presentation.views.usecases.MainActivity
import com.au.fridgly.presentation.views.usecases.search.adapter.RecipeThumbnailGridRecyclerAdapter
import javax.inject.Inject

private const val ARG_SEARCH_QUERY = "ARG_SEARCH_QUERY"
class FragmentSearchResults : Fragment(), ISearchResultContract.View {

    private var listener: OnFragmentInteractionListener? = null
    private lateinit var query: String

    @BindView(R.id.fragment_search_results_recyclerView_thumbnail)
    lateinit var thumbnailRecyclerView: RecyclerView

    private lateinit var presenter: SearchResultPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            query = it.getString(ARG_SEARCH_QUERY)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search_results, container, false)
        (activity as MainActivity).getComponent().inject(this)
        ButterKnife.bind(this, view)
        thumbnailRecyclerView.layoutManager = GridLayoutManager(context, 2)
        thumbnailRecyclerView.setHasFixedSize(true)

        if (!query.isBlank())
            this.presenter.getSearchRecipes(100, query)
        else
            showToast("It's empty!")

        return view
    }

    //region Inject Fields

    @Inject
    fun setPresenter(presenter: SearchResultPresenter){
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

    override fun updateThumbnails(list: List<RecipeThumbnail>) {
        thumbnailRecyclerView.adapter = RecipeThumbnailGridRecyclerAdapter(list, this)
    }

    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun getViewActivity(): Activity {
        return activity!!
    }

    interface OnFragmentInteractionListener

    companion object {
        @JvmStatic
        fun newInstance(query: String) =
            FragmentSearchResults().apply {
                arguments = Bundle().apply {
                    putString(ARG_SEARCH_QUERY, query)
                }
            }
    }
}
