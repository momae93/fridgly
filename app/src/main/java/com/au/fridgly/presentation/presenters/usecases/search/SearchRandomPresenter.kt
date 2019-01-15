package com.au.fridgly.presentation.presenters.usecases.search

import com.au.fridgly.domain.interactors.AbstractObserver
import com.au.fridgly.domain.interactors.search.GetRandomRecipes
import com.au.fridgly.domain.models.RecipeThumbnail
import com.au.fridgly.presentation.contracts.search.ISearchRandomContract
import javax.inject.Inject

class SearchRandomPresenter: ISearchRandomContract.Presenter {

    private lateinit var view: ISearchRandomContract.View
    private var getRandomRecipes: GetRandomRecipes

    @Inject constructor(getRandomRecipes: GetRandomRecipes){
        this.getRandomRecipes = getRandomRecipes
    }

    fun setView(view: ISearchRandomContract.View){
        this.view = view
    }

    override fun getRandomRecipe(number: Int) {
        val key = "n9UfX4ANwYmshDzNDfneXaw7zrHDp1Z7ox1jsnBlCvCeH3l5SG"
        getRandomRecipes.execute(SearchRandomRecipeObserver(), GetRandomRecipes.Params(key, number))
    }

    override fun resume() {}

    override fun pause() {}

    override fun destroy() {}

    private inner class SearchRandomRecipeObserver : AbstractObserver<List<RecipeThumbnail>>() {

        override fun onComplete() {}

        override fun onError(e: Throwable) {
            val message = "An error occured"
            this@SearchRandomPresenter.view.showToast(message)
            //this@ProfileTagPresenter.showError(e.toErrorAPI())
        }

        override fun onNext(list: List<RecipeThumbnail>) {
            if (list.size > 1){
                this@SearchRandomPresenter.view.updateMainThumbnail(list[0])
                this@SearchRandomPresenter.view.updateExtraThumbnail(list.drop(1))
            }
            else{
                val message = "The list is too short"
                this@SearchRandomPresenter.view.showToast(message)
            }
        }
    }
}