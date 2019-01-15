package com.au.fridgly.domain.interactors.search

import com.au.fridgly.domain.executors.PostExecutionThread
import com.au.fridgly.domain.executors.ThreadExecutor
import com.au.fridgly.domain.interactors.AbstractUsecase
import com.au.fridgly.domain.models.RecipeThumbnail
import com.au.fridgly.domain.repository.ISearchRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetSearchRecipes: AbstractUsecase<List<RecipeThumbnail>, GetSearchRecipes.Params> {
    private var searchRepository: ISearchRepository

    override fun buildUseCaseObservable(params: GetSearchRecipes.Params): Observable<List<RecipeThumbnail>> {
        return searchRepository.getSearchRecipe(params.key, params.number, params.ingredients)
    }

    @Inject
    constructor(searchRepository: ISearchRepository, threadExecutor: ThreadExecutor,
                postExecutionThread: PostExecutionThread
    ): super(threadExecutor, postExecutionThread) {
        this.searchRepository = searchRepository
    }

    class Params constructor(val key: String, val number: Int, val ingredients: String)
}