package com.au.fridgly.presentation.presenters.usecases.recipe

import com.au.fridgly.domain.interactors.AbstractObserver
import com.au.fridgly.domain.interactors.search.GetRecipeDetailsById
import com.au.fridgly.domain.models.RecipeDetails
import com.au.fridgly.presentation.contracts.recipe.IRecipeDetailsContract
import javax.inject.Inject

class RecipeDetailsPresenter: IRecipeDetailsContract.Presenter {

    private lateinit var view: IRecipeDetailsContract.View

    private var getRecipeDetailsById: GetRecipeDetailsById

    @Inject constructor(getRecipeDetailsById: GetRecipeDetailsById){
        this.getRecipeDetailsById = getRecipeDetailsById
    }

    fun setView(view: IRecipeDetailsContract.View){
        this.view = view
    }

    override fun resume() {}

    override fun pause() {}

    override fun destroy() {}

    override fun getRecipeDetails(idRecipe: Int) {
        val key = "n9UfX4ANwYmshDzNDfneXaw7zrHDp1Z7ox1jsnBlCvCeH3l5SG"
        return getRecipeDetailsById.execute(RecipeDetailsObserver(), GetRecipeDetailsById.Params(key, idRecipe))
    }

    private inner class RecipeDetailsObserver : AbstractObserver<RecipeDetails>() {

        override fun onComplete() {}

        override fun onError(e: Throwable) {
            val message = "An error occured"
            this@RecipeDetailsPresenter.view.showToast(message)
        }

        override fun onNext(recipe: RecipeDetails) {
            this@RecipeDetailsPresenter.view.updateRecipeDetails(recipe)
        }
    }
}