package com.au.fridgly.presentation.contracts.search

import com.au.fridgly.domain.models.RecipeThumbnail
import com.au.fridgly.presentation.contracts.BasePresenter
import com.au.fridgly.presentation.contracts.BaseView

interface ISearchRandomContract {
    interface View: BaseView {
        fun updateMainThumbnail(thumbnail: RecipeThumbnail)
        fun updateExtraThumbnail(list: List<RecipeThumbnail>)
        fun loading(isLoading: Boolean)
    }

    interface Presenter: BasePresenter {
        fun getRandomRecipe(number: Int)
    }
}
