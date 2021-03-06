package com.au.fridgly.presentation.presenters.usecases.recipe

import com.au.fridgly.domain.interactors.AbstractObserver
import com.au.fridgly.domain.interactors.favorite.GetIsFavoriteRecipeById
import com.au.fridgly.domain.interactors.favorite.InsertFavoriteRecipe
import com.au.fridgly.domain.interactors.historical.InsertHistorical
import com.au.fridgly.domain.interactors.search.GetRecipeDetailsById
import com.au.fridgly.domain.models.RecipeThumbnail
import com.au.fridgly.domain.models.recipeDetails.RecipeDetails
import com.au.fridgly.presentation.contracts.recipe.IRecipeDetailsContract
import com.au.fridgly.presentation.models.EState
import javax.inject.Inject

class RecipeDetailsPresenter: IRecipeDetailsContract.Presenter {

    private lateinit var view: IRecipeDetailsContract.View

    private var getRecipeDetailsById: GetRecipeDetailsById
    private var insertFavoriteRecipe : InsertFavoriteRecipe
    private var getIsFavoriteRecipeById : GetIsFavoriteRecipeById
    private var insertHistorical : InsertHistorical


    @Inject constructor(getRecipeDetailsById: GetRecipeDetailsById,
                        insertFavoriteRecipe : InsertFavoriteRecipe,
                        getIsFavoriteRecipeById : GetIsFavoriteRecipeById,
                        insertHistorical : InsertHistorical){
        this.getRecipeDetailsById = getRecipeDetailsById
        this.insertFavoriteRecipe = insertFavoriteRecipe
        this.getIsFavoriteRecipeById = getIsFavoriteRecipeById
        this.insertHistorical = insertHistorical
    }

    fun setView(view: IRecipeDetailsContract.View){
        this.view = view
    }

    override fun resume() {}

    override fun pause() {}

    override fun destroy() {}

    override fun getRecipeDetails(idRecipe: Int) {
        this@RecipeDetailsPresenter.view.handleState(EState.LOADING)
        val key = "n9UfX4ANwYmshDzNDfneXaw7zrHDp1Z7ox1jsnBlCvCeH3l5SG"
        return getRecipeDetailsById.execute(RecipeDetailsObserver(), GetRecipeDetailsById.Params(key, idRecipe))
    }

    override fun getIsFavorite(id: Int) {
        return getIsFavoriteRecipeById.execute(GetIsFavoriteRecipeObserver(), GetIsFavoriteRecipeById
            .Params(view.getViewActivity().applicationContext, id))
    }

    override fun postFavorite(recipe: RecipeDetails) {
        val recipeThumbnail = RecipeThumbnail(id = recipe.id, image = recipe.image, name = recipe.name)

        return insertFavoriteRecipe.execute(PostFavoriteRecipeObserver(), InsertFavoriteRecipe
            .Params(view.getViewActivity().applicationContext, recipeThumbnail))
    }

    override fun postHistorical(recipe: RecipeDetails) {
        val recipeThumbnail = RecipeThumbnail(id = recipe.id, image = recipe.image, name = recipe.name)

        return insertHistorical.execute(PostHistoricalRecipeObserver(), InsertHistorical
            .Params(view.getViewActivity().applicationContext, recipeThumbnail))
    }

    private inner class RecipeDetailsObserver : AbstractObserver<RecipeDetails>() {
        override fun onComplete() {
            this@RecipeDetailsPresenter.view.handleState(EState.LOADED)
        }

        override fun onError(e: Throwable) {
            this@RecipeDetailsPresenter.view.handleState(EState.ERROR)
        }

        override fun onNext(recipe: RecipeDetails) {
            this@RecipeDetailsPresenter.view.updateRecipeDetails(recipe)
        }
    }

    private inner class GetIsFavoriteRecipeObserver : AbstractObserver<Boolean>() {
        override fun onComplete() {
        }

        override fun onError(e: Throwable) {
            val message = "An error occured"
            this@RecipeDetailsPresenter.view.showToast(message)
        }

        override fun onNext(t: Boolean) {
            this@RecipeDetailsPresenter.view.updateFavoriteIcon(t)
        }
    }

    private inner class PostFavoriteRecipeObserver : AbstractObserver<Boolean>() {
        override fun onComplete() {
        }

        override fun onError(e: Throwable) {
            val message = "An error occured"
            this@RecipeDetailsPresenter.view.showToast(message)
        }

        override fun onNext(t: Boolean) {
            this@RecipeDetailsPresenter.view.updateFavoriteIcon(t)
        }
    }

    private inner class PostHistoricalRecipeObserver : AbstractObserver<Void>() {
        override fun onComplete() {
        }

        override fun onError(e: Throwable) {
            val message = "An error occured"
            this@RecipeDetailsPresenter.view.showToast(message)
        }
    }
}