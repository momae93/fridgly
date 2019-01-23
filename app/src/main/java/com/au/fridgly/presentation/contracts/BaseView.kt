package com.au.fridgly.presentation.contracts

import android.app.Activity
import com.au.fridgly.presentation.models.EState

interface BaseView {
    fun showToast(message: String)
    fun handleState(state: EState)
    fun getViewActivity(): Activity
}