package com.au.fridgly.presentation.presenters.usecases.recipe

import com.au.fridgly.domain.interactors.AbstractObserver
import com.au.fridgly.domain.interactors.favorite.GetIsFavoriteRecipeById
import com.au.fridgly.domain.interactors.favorite.InsertFavoriteRecipe
import com.au.fridgly.domain.interactors.search.GetRecipeDetailsById
import com.au.fridgly.domain.models.RecipeThumbnail
import com.au.fridgly.domain.models.recipeDetails.RecipeDetails
import com.au.fridgly.presentation.contracts.recipe.IRecipeDetailsContract
import javax.inject.Inject

class RecipeDetailsPresenter: IRecipeDetailsContract.Presenter {

    private lateinit var view: IRecipeDetailsContract.View

    private var getRecipeDetailsById: GetRecipeDetailsById
    private var insertFavoriteRecipe : InsertFavoriteRecipe
    private var getIsFavoriteRecipeById : GetIsFavoriteRecipeById
    private lateinit var recipeDetails: RecipeDetails

    @Inject constructor(getRecipeDetailsById: GetRecipeDetailsById,
                        insertFavoriteRecipe : InsertFavoriteRecipe,
                        getIsFavoriteRecipeById : GetIsFavoriteRecipeById){
        this.getRecipeDetailsById = getRecipeDetailsById
        this.insertFavoriteRecipe = insertFavoriteRecipe
        this.getIsFavoriteRecipeById = getIsFavoriteRecipeById
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

    override fun getIsFavorite(id: Int) {
        return getIsFavoriteRecipeById.execute(GetIsFavoriteRecipeObserver(), GetIsFavoriteRecipeById
            .Params(view.getViewActivity().applicationContext, id))
    }

    override fun postFavorite(recipe: RecipeDetails) {
        recipeDetails = recipe
        val recipeThumbnail = RecipeThumbnail(id = recipe.id, image = recipe.image, name = recipe.name)

        return insertFavoriteRecipe.execute(PostFavoriteRecipeObserver(), InsertFavoriteRecipe
            .Params(view.getViewActivity().applicationContext, recipeThumbnail))
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
}