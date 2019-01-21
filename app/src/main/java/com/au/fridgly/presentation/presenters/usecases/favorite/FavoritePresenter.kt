package com.au.fridgly.presentation.presenters.usecases.favorite

import com.au.fridgly.domain.interactors.AbstractObserver
import com.au.fridgly.domain.interactors.favorite.GetAllFavoriteRecipes
import com.au.fridgly.domain.interactors.search.GetRandomRecipes
import com.au.fridgly.domain.interactors.search.GetSearchRecipes
import com.au.fridgly.domain.models.RecipeThumbnail
import com.au.fridgly.presentation.contracts.favorite.IFavoriteContract
import javax.inject.Inject

class FavoritePresenter: IFavoriteContract.Presenter {

    private lateinit var view: IFavoriteContract.View
    private var getAllFavoriteRecipes: GetAllFavoriteRecipes

    @Inject constructor(getAllFavoriteRecipes: GetAllFavoriteRecipes){
        this.getAllFavoriteRecipes = getAllFavoriteRecipes
    }

    fun setView(view: IFavoriteContract.View){
        this.view = view
    }

    override fun resume() {}

    override fun pause() {}

    override fun destroy() {}

    override fun getFavoriteRecipes() {
        val param =  GetAllFavoriteRecipes
            .Params(view.getViewActivity().applicationContext)
        getAllFavoriteRecipes.execute(FavoriteRecipeObserver(), param)
    }

    override fun getRecentRecipes() {
    }

    private inner class FavoriteRecipeObserver : AbstractObserver<List<RecipeThumbnail>>() {

        override fun onComplete() {}

        override fun onError(e: Throwable) {
            val message = "An error occured"
            this@FavoritePresenter.view.showToast(message)
        }

        override fun onNext(list: List<RecipeThumbnail>) {
            this@FavoritePresenter.view.updateFavoriteRecipes(list)
        }
    }
}