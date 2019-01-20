package com.au.fridgly.presentation.contracts.recipe

import com.au.fridgly.presentation.contracts.BasePresenter
import com.au.fridgly.presentation.contracts.BaseView

interface IRecipeStepContract {
    interface View: BaseView {
        fun updateStepDetails()
        fun updateNavigationButton()
        fun nextStep()
        fun previousStep()
    }

    interface Presenter: BasePresenter {
    }
}