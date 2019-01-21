package com.au.fridgly.domain.interactors.historical

import android.content.Context
import com.au.fridgly.domain.executors.PostExecutionThread
import com.au.fridgly.domain.executors.ThreadExecutor
import com.au.fridgly.domain.interactors.AbstractUsecase
import com.au.fridgly.domain.models.RecipeThumbnail
import com.au.fridgly.domain.repository.IFavoriteRepository
import com.au.fridgly.domain.repository.IHistoricalRepository
import io.reactivex.Observable
import javax.inject.Inject

class InsertHistorical: AbstractUsecase<Void, InsertHistorical.Params> {
    private var historicalRepository: IHistoricalRepository

    override fun buildUseCaseObservable(params: InsertHistorical.Params): Observable<Void> {
        return historicalRepository.insertHistorical(params.context, params.recipeThumbnail)
    }

    @Inject
    constructor(historicalRepository: IHistoricalRepository, threadExecutor: ThreadExecutor,
                postExecutionThread: PostExecutionThread
    ): super(threadExecutor, postExecutionThread) {
        this.historicalRepository = historicalRepository
    }

    class Params constructor(val context: Context, val recipeThumbnail: RecipeThumbnail)
}