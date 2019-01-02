package com.au.fridgly.domain.interactors

import io.reactivex.observers.DisposableObserver

open class AbstractObserver<T>: DisposableObserver<T>() {
    override fun onComplete() {
    }

    override fun onNext(t: T) {
    }

    override fun onError(e: Throwable) {
    }
}