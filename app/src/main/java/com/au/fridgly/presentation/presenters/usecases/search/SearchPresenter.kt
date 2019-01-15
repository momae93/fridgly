package com.au.fridgly.presentation.presenters.usecases.search

import com.au.fridgly.presentation.contracts.search.ISearchContract
import javax.inject.Inject

class SearchPresenter: ISearchContract.Presenter {

    private lateinit var view: ISearchContract.View

    @Inject constructor()

    fun setView(view: ISearchContract.View){
        this.view = view
    }

    override fun resume() {}

    override fun pause() {}

    override fun destroy() {}

}