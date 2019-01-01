package com.au.fridgly.presentation.contracts

interface BasePresenter<in T> {
    fun resume()

    fun pause()

    fun destroy()
}