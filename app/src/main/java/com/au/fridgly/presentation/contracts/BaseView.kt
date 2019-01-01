package com.au.fridgly.presentation.contracts

interface BaseView<in T> {
    fun showToast(message: String)
}