package com.au.fridgly.presentation.contracts

import android.app.Activity

interface BaseView<in T> {
    fun showToast(message: String)
    fun getViewActivity(): Activity
}