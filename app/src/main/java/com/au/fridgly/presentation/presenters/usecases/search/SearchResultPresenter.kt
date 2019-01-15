package com.au.fridgly.presentation.presenters.usecases.search

import com.au.fridgly.domain.interactors.AbstractObserver
import com.au.fridgly.domain.interactors.search.GetSearchRecipes
import com.au.fridgly.domain.models.RecipeThumbnail
import com.au.fridgly.presentation.contracts.search.ISearchResultContract
import javax.inject.Inject

class SearchResultPresenter: ISearchResultContract.Presenter {

    private lateinit var view: ISearchResultContract.View
    private var getSearchRecipes: GetSearchRecipes

    @Inject constructor(getSearchRecipes: GetSearchRecipes){
        this.getSearchRecipes = getSearchRecipes
    }

    fun setView(view: ISearchResultContract.View){
        this.view = view
    }

    override fun getSearchRecipes(number: Int, ingredient: String) {
        val key = "n9UfX4ANwYmshDzNDfneXaw7zrHDp1Z7ox1jsnBlCvCeH3l5SG"
        getSearchRecipes.execute(SearchRandomRecipeObserver(), GetSearchRecipes.Params(key, number, ingredient))
    }

    override fun resume() {}

    override fun pause() {}

    override fun destroy() {}

    private inner class SearchRandomRecipeObserver : AbstractObserver<List<RecipeThumbnail>>() {

        override fun onComplete() {}

        override fun onError(e: Throwable) {
            val message = "An error occured"
            this@SearchResultPresenter.view.showToast(message)
        }

        override fun onNext(list: List<RecipeThumbnail>) {
            this@SearchResultPresenter.view.updateThumbnails(list)
        }
    }
}