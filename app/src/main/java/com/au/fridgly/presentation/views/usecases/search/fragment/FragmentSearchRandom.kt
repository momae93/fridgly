package com.au.fridgly.presentation.views.usecases.search.fragment

import android.app.Activity
import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.support.constraint.Group
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.airbnb.lottie.LottieAnimationView
import com.au.fridgly.R
import com.au.fridgly.domain.models.RecipeThumbnail
import com.au.fridgly.presentation.contracts.search.ISearchRandomContract
import com.au.fridgly.presentation.presenters.usecases.search.SearchRandomPresenter
import com.au.fridgly.presentation.views.usecases.BaseActivity
import com.au.fridgly.presentation.views.usecases.MainActivity
import com.au.fridgly.presentation.views.usecases.recipe.fragment.DialogFragmentRecipe
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

    @BindView(R.id.fragment_search_random_group_loaded)
    lateinit var loadedGroup: Group

    @BindView(R.id.fragment_search_random_group_loading)
    lateinit var loadingGroup: Group

    @BindView(R.id.fragment_search_random_animation_loading)
    lateinit var loadingAnimation: LottieAnimationView

    private lateinit var presenter: SearchRandomPresenter

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search_random, container, false)
        ButterKnife.bind(this, view)
        (activity as MainActivity).getComponent().inject(this)
        thumbnailRecyclerView.layoutManager = GridLayoutManager(context, 1, GridLayout.HORIZONTAL, false)
        thumbnailRecyclerView.setHasFixedSize(true)
        this.presenter.getRandomRecipe(10)

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
        thumbnail.apply {
            nameTextView.text = name
            if (!image.isNullOrBlank()){
                Glide.with(activity!!).load(image).into(pictureImageView)
                pictureImageView.setOnClickListener {
                    (activity as BaseActivity).showDialog(DialogFragmentRecipe.newInstance(id))
                }
            }
        }
    }

    override fun updateExtraThumbnail(list: List<RecipeThumbnail>) {
        thumbnailRecyclerView.adapter = RecipeThumbnailRecyclerAdapter(list, this)
    }

    override fun loading(isLoading: Boolean) {
        if (isLoading){
            loadingGroup.visibility = View.VISIBLE
            loadedGroup.visibility = View.GONE
            loadingAnimation.playAnimation()
        }
        else{
            loadedGroup.visibility = View.VISIBLE
            loadingGroup.visibility = View.GONE
            loadingAnimation.cancelAnimation()
        }
    }

    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun getViewActivity(): Activity {
        return activity!!
    }

    interface OnFragmentInteractionListener
}
