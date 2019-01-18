package com.au.fridgly.domain.interactors.search

import com.au.fridgly.domain.executors.PostExecutionThread
import com.au.fridgly.domain.executors.ThreadExecutor
import com.au.fridgly.domain.interactors.AbstractUsecase
import com.au.fridgly.domain.models.RecipeDetails
import com.au.fridgly.domain.repository.ISearchRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetRecipeDetailsById: AbstractUsecase<RecipeDetails, GetRecipeDetailsById.Params> {
    private var searchRepository: ISearchRepository

    override fun buildUseCaseObservable(params: GetRecipeDetailsById.Params): Observable<RecipeDetails> {
        return searchRepository.getRecipeDetailsById(params.key, params.idRecipe)
    }

    @Inject
    constructor(searchRepository: ISearchRepository, threadExecutor: ThreadExecutor,
                postExecutionThread: PostExecutionThread
    ): super(threadExecutor, postExecutionThread) {
        this.searchRepository = searchRepository
    }

    class Params constructor(val key: String, val idRecipe: Int)
}