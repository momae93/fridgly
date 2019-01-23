package com.au.fridgly.presentation.contracts.search

import android.graphics.Bitmap
import com.au.fridgly.presentation.contracts.BasePresenter
import com.au.fridgly.presentation.contracts.BaseView

interface ISearchContract {
    interface View: BaseView

    interface Presenter: BasePresenter {
        fun analyzeBitmap(bitmap: Bitmap)
    }
}
