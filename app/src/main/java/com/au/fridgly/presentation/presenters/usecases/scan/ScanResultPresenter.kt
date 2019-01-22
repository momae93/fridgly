package com.au.fridgly.presentation.presenters.usecases.scan

import com.au.fridgly.domain.models.EAliment
import com.au.fridgly.presentation.contracts.scan.IScanResultContract
import javax.inject.Inject

class ScanResultPresenter: IScanResultContract.Presenter {

    private lateinit var view: IScanResultContract.View

    @Inject constructor()

    fun setView(view: IScanResultContract.View){
        this.view = view
    }

    override fun getAliments(list: List<String>) {
        val aliments = list.map{EAliment.find(it)}
        if (aliments.count() > 0)
            this.view.updateMainAliment(aliments[0])
        if (aliments.count() > 1)
            this.view.updateExtraAliment(aliments.drop(1))
    }

    override fun resume() {}

    override fun pause() {}

    override fun destroy() {}

}