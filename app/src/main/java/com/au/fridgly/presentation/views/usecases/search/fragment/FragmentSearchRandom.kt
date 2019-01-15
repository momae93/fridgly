package com.au.fridgly.presentation.views.usecases.search.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import com.au.fridgly.R
import com.au.fridgly.domain.models.RecipeThumbnail
import com.au.fridgly.presentation.contracts.search.ISearchRandomContract
import com.au.fridgly.presentation.presenters.usecases.search.SearchRandomPresenter
import com.au.fridgly.presentation.views.usecases.search.adapter.RecipeThumbnailRecyclerAdapter
import com.bumptech.glide.Glide
import javax.inject.Inject

class FragmentSearchRandom : Fragment(), ISearchRandomContract.View {

    @BindView(R.id.fragment_search_random_textView_name)
    lateinit var nameTextView: TextView

    @BindView(R.id.fragment_search_random_imageView_image)
    lateinit var pictureImageView: ImageView

    @BindView(R.id.fragment_search_random_recyclerView_thumbnails)
    lateinit var thumbnailRecyclerView: RecyclerView

    private lateinit var presenter: SearchRandomPresenter

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search_random, container, false)
        return view
    }

    //region Inject Fields

    @Inject
    fun setPresenter(presenter: SearchRandomPresenter){
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

    override fun updateMainThumbnail(thumbnail: RecipeThumbnail) {
        nameTextView.text = thumbnail.name
        if (!thumbnail.image.isBlank()){
            Glide.with(activity!!).load(thumbnail.image).into(pictureImageView)
        }
    }

    override fun updateExtraThumbnail(list: List<RecipeThumbnail>) {
        thumbnailRecyclerView.adapter = RecipeThumbnailRecyclerAdapter(list, this)
    }

    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun getViewActivity(): Activity {
        return activity!!
    }

    interface OnFragmentInteractionListener
}
