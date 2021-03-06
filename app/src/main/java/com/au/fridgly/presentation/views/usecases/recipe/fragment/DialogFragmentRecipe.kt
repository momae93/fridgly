package com.au.fridgly.presentation.views.usecases.recipe.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.constraint.Group
import android.support.v4.app.DialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import butterknife.BindView
import butterknife.ButterKnife
import com.airbnb.lottie.LottieAnimationView
import com.au.fridgly.R
import com.au.fridgly.domain.models.recipeDetails.RecipeDetails
import com.au.fridgly.presentation.contracts.recipe.IRecipeDetailsContract
import com.au.fridgly.presentation.models.EState
import com.au.fridgly.presentation.models.recipeDetails.StepUI
import com.au.fridgly.presentation.presenters.helpers.RecipeDetailsMapper
import com.au.fridgly.presentation.presenters.usecases.recipe.RecipeDetailsPresenter
import com.au.fridgly.presentation.views.usecases.BaseActivity
import com.au.fridgly.presentation.views.usecases.MainActivity
import com.au.fridgly.presentation.views.usecases.recipe.adapter.RecipeExtendedIngredientRecylerAdapter
import com.bumptech.glide.Glide
import javax.inject.Inject

private const val ARG_RECIPE_ID = "ARG_RECIPE_ID"

class DialogFragmentRecipe : DialogFragment(), IRecipeDetailsContract.View {

    private var idRecipe: Int = 0
    private var listener: OnFragmentInteractionListener? = null

    @BindView(R.id.fragment_recipe_button_back)
    lateinit var backButton: ImageButton

    @BindView(R.id.fragment_recipe_button_favorite)
    lateinit var favoriteButton: ImageButton

    @BindView(R.id.fragment_recipe_textView_title)
    lateinit var titleTextView: TextView

    @BindView(R.id.fragment_recipe_imageView_image)
    lateinit var pictureImageView: ImageView

    @BindView(R.id.fragment_recipe_textView_servings)
    lateinit var servingsTextView: TextView

    @BindView(R.id.fragment_recipe_textView_ready)
    lateinit var readyTextView: TextView

    @BindView(R.id.fragment_recipe_textView_preparation)
    lateinit var preparationTextView: TextView

    @BindView(R.id.fragment_recipe_recyclerView_ingredients)
    lateinit var ingredientsRecyclerView: RecyclerView

    @BindView(R.id.fragment_recipe_textView_instructions)
    lateinit var instructionsTextView: TextView

    @BindView(R.id.fragment_recipe_imageButton_steps)
    lateinit var stepImageButton: ImageButton

    @BindView(R.id.fragment_recipe_group_loaded)
    lateinit var loadedGroup: Group

    @BindView(R.id.fragment_recipe_group_loading)
    lateinit var loadingGroup: Group

    @BindView(R.id.fragment_recipe_group_no_internet)
    lateinit var noInternetGroup: Group

    @BindView(R.id.fragment_recipe_group_error)
    lateinit var errorGroup: Group

    @BindView(R.id.fragment_recipe_button_refresh)
    lateinit var refreshButton: Button

    @BindView(R.id.fragment_recipe_animation_loading)
    lateinit var loadingAnimation: LottieAnimationView

    private lateinit var presenter: RecipeDetailsPresenter
    private lateinit var recipeDetails: RecipeDetails

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle)
        arguments?.let {
            idRecipe = it.getInt(ARG_RECIPE_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_fragment_recipe, container, false)
        ButterKnife.bind(this, view)
        (activity as MainActivity).getComponent().inject(this)
        ingredientsRecyclerView.layoutManager = LinearLayoutManager(context)

        favoriteButton.setOnClickListener { this.presenter.postFavorite(recipeDetails) }
        backButton.setOnClickListener { this.dismiss() }

        this.getRecipeDetails()
        this.presenter.getIsFavorite(idRecipe)
        return view
    }

    //region Inject Fields

    @Inject
    fun setPresenter(presenter: RecipeDetailsPresenter){
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

    interface OnFragmentInteractionListener


    override fun updateRecipeDetails(recipe: RecipeDetails) {
        this.recipeDetails = recipe
        this.titleTextView.text = recipe.name
        this.servingsTextView.text = recipe.servings.toString()
        this.readyTextView.text = "${recipe.ready} min"
        this.preparationTextView.text = if (recipe.preparation == null) "N/A" else "${recipe.preparation} min"
        this.instructionsTextView.text = recipe.instructions

        if (recipe.steps != null && recipe.steps.count() > 0){
            stepImageButton.visibility = View.VISIBLE
            val steps: List<StepUI> = recipe.steps.map{RecipeDetailsMapper().transform(it)}
            stepImageButton.setOnClickListener {
                (activity as BaseActivity).showDialog(DialogFragmentStep.newInstance(ArrayList(steps)))
            }
        }
        if (!recipe.image.isNullOrBlank()){
            Glide.with(activity!!).load(recipe.image).into(pictureImageView)
        }
        this.ingredientsRecyclerView.adapter = RecipeExtendedIngredientRecylerAdapter(recipe.ingredients, this)
        this.presenter.postHistorical(recipe)
    }

    override fun updateFavoriteIcon(isFavorite: Boolean) {
        val drawable = if (isFavorite) R.drawable.ic_heart else R.drawable.ic_heart_empty
        favoriteButton.setBackgroundResource(drawable)
    }

    override fun getRecipeDetails() {
        if (checkConnection())
            this.presenter.getRecipeDetails(idRecipe)
    }

    override fun checkConnection(): Boolean {
        if (!(activity as BaseActivity).checkInternetConnection()) {
            this.handleState(EState.NO_INTERNET)
            return false
        }
        return true
    }

    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun getViewActivity(): Activity {
        return activity!!
    }

    override fun handleState(state: EState) {
        loadedGroup.visibility = View.GONE
        loadingGroup.visibility = View.GONE
        noInternetGroup.visibility = View.GONE
        errorGroup.visibility = View.GONE
        refreshButton.visibility = View.GONE
        when (state){
            EState.LOADED -> {
                loadedGroup.visibility = View.VISIBLE
                loadingAnimation.cancelAnimation()
            }
            EState.LOADING -> {
                loadingGroup.visibility = View.VISIBLE
                loadingAnimation.playAnimation()
            }
            EState.NO_INTERNET -> {
                noInternetGroup.visibility = View.VISIBLE
                refreshButton.visibility = View.VISIBLE
                refreshButton.setOnClickListener { this.getRecipeDetails() }
            }
            EState.ERROR -> {
                errorGroup.visibility = View.VISIBLE
                refreshButton.visibility = View.VISIBLE
                refreshButton.setOnClickListener { this.getRecipeDetails() }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(idRecipe: Int) =
            DialogFragmentRecipe().apply {
                arguments = Bundle().apply {
                    putInt(ARG_RECIPE_ID, idRecipe)
                }
            }
    }
}
