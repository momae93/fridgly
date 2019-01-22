package com.au.fridgly.presentation.contracts.scan

import com.au.fridgly.domain.models.EAliment
import com.au.fridgly.presentation.contracts.BasePresenter
import com.au.fridgly.presentation.contracts.BaseView

interface IScanResultContract {
    interface View: BaseView {
        fun updateMainAliment(aliment: EAliment)
        fun updateExtraAliment(list: List<EAliment>)
        fun dismiss()
    }

    interface Presenter: BasePresenter {
        fun getAliments(list: List<String>)
    }
}