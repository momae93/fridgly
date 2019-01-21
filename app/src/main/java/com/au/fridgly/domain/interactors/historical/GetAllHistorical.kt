package com.au.fridgly.domain.interactors.historical

import android.content.Context
import com.au.fridgly.domain.executors.PostExecutionThread
import com.au.fridgly.domain.executors.ThreadExecutor
import com.au.fridgly.domain.interactors.AbstractUsecase
import com.au.fridgly.domain.models.RecipeThumbnail
import com.au.fridgly.domain.repository.IHistoricalRepository
import com.au.fridgly.domain.repository.ISearchRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetAllHistorical: AbstractUsecase<List<RecipeThumbnail>, GetAllHistorical.Params> {
    private var historicalRepository: IHistoricalRepository

    override fun buildUseCaseObservable(params: GetAllHistorical.Params): Observable<List<RecipeThumbnail>> {
        return historicalRepository.getHistorical(params.context)
    }

    @Inject
    constructor(historicalRepository: IHistoricalRepository, threadExecutor: ThreadExecutor,
                postExecutionThread: PostExecutionThread
    ): super(threadExecutor, postExecutionThread) {
        this.historicalRepository = historicalRepository
    }

    class Params constructor(val context: Context)
}