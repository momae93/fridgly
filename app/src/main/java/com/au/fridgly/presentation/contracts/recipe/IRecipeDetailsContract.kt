package com.au.fridgly.presentation.contracts.recipe

import com.au.fridgly.domain.models.recipeDetails.RecipeDetails
import com.au.fridgly.presentation.contracts.BasePresenter
import com.au.fridgly.presentation.contracts.BaseView

interface IRecipeDetailsContract {
    interface View: BaseView {
        fun updateRecipeDetails(recipe: RecipeDetails)
        fun updateFavoriteIcon(isFavorite: Boolean)
    }

    interface Presenter: BasePresenter {
        fun getRecipeDetails(idRecipe: Int)
        fun getIsFavorite(id: Int)
        fun postFavorite(recipe: RecipeDetails)
    }
}