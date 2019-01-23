package com.au.fridgly.presentation.contracts.search

import com.au.fridgly.domain.models.RecipeThumbnail
import com.au.fridgly.presentation.contracts.BasePresenter
import com.au.fridgly.presentation.contracts.BaseView

interface ISearchResultContract {
    interface View: BaseView {
        fun updateThumbnails(list: List<RecipeThumbnail>)
        fun getSearchRecipes()
        fun checkConnection(): Boolean
    }

    interface Presenter: BasePresenter {
        fun getSearchRecipes(number: Int, ingredient: String)
    }
}