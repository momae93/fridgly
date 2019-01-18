package com.au.fridgly.presentation.contracts.recipe

import com.au.fridgly.domain.models.RecipeDetails
import com.au.fridgly.presentation.contracts.BasePresenter
import com.au.fridgly.presentation.contracts.BaseView

interface IRecipeDetailsContract {
    interface View: BaseView {
        fun updateRecipeDetails(recipe: RecipeDetails)
    }

    interface Presenter: BasePresenter {
        fun getRecipeDetails(idRecipe: Int)
    }
}