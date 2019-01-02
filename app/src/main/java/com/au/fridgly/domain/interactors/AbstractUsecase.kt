package com.au.fridgly.domain.interactors

import com.au.fridgly.domain.executors.PostExecutionThread
import com.au.fridgly.domain.executors.ThreadExecutor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class AbstractUsecase<T, in Params>(private val threadExecutor: ThreadExecutor,
                                             private val postExecutionThread: PostExecutionThread
) {

    private val disposables: CompositeDisposable = CompositeDisposable()

    internal abstract fun buildUseCaseObservable(params: Params): Observable<T>

    fun execute(observer: DisposableObserver<T>, params: Params){
        checkNotNull(observer)

        val observable = this.buildUseCaseObservable(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.getScheduler())

        this.addDisposable(observable.subscribeWith(observer))
    }

    fun dispose(){
        if (!disposables.isDisposed)
            disposables.dispose()
    }

    private fun addDisposable(disposable: Disposable) {
        checkNotNull(disposable)
        checkNotNull(disposables)

        disposables.add(disposable)
    }
}