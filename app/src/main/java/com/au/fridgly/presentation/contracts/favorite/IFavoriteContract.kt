package com.au.fridgly.presentation.contracts.favorite

import com.au.fridgly.domain.models.RecipeThumbnail
import com.au.fridgly.presentation.contracts.BasePresenter
import com.au.fridgly.presentation.contracts.BaseView

interface IFavoriteContract {
    interface View: BaseView {
        fun updateFavoriteRecipes(list: List<RecipeThumbnail>)
        fun updateRecentRecipes(list: List<RecipeThumbnail>)
    }

    interface Presenter: BasePresenter {
        fun getFavoriteRecipes()
        fun getRecentRecipes()
    }
}