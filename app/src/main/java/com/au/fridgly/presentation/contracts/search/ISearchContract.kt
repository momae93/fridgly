package com.au.fridgly.presentation.contracts.search

import com.au.fridgly.domain.models.RecipeThumbnail
import com.au.fridgly.presentation.contracts.BasePresenter
import com.au.fridgly.presentation.contracts.BaseView

interface ISearchContract {
    interface View: BaseView<Presenter> {
        fun updateMainThumbnail(thumbnail: RecipeThumbnail)
        fun updateExtraThumbnail(list: List<RecipeThumbnail>)
    }

    interface Presenter: BasePresenter<View> {
        fun getRandomRecipe(number: Int)
    }
}
