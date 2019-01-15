package com.au.fridgly.domain.interactors.search

import com.au.fridgly.domain.executors.PostExecutionThread
import com.au.fridgly.domain.executors.ThreadExecutor
import com.au.fridgly.domain.interactors.AbstractUsecase
import com.au.fridgly.domain.models.RecipeThumbnail
import com.au.fridgly.domain.repository.ISearchRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetRandomRecipes: AbstractUsecase<List<RecipeThumbnail>, GetRandomRecipes.Params> {
    private var searchRepository: ISearchRepository

    override fun buildUseCaseObservable(params: GetRandomRecipes.Params): Observable<List<RecipeThumbnail>> {
        return searchRepository.getRandomRecipe(params.key, params.number)
    }

    @Inject
    constructor(searchRepository: ISearchRepository, threadExecutor: ThreadExecutor,
                postExecutionThread: PostExecutionThread
    ): super(threadExecutor, postExecutionThread) {
        this.searchRepository = searchRepository
    }

    class Params constructor(val key: String, val number: Int)
}