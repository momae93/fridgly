package com.au.fridgly.domain.interactors.favorite

import android.content.Context
import com.au.fridgly.domain.executors.PostExecutionThread
import com.au.fridgly.domain.executors.ThreadExecutor
import com.au.fridgly.domain.interactors.AbstractUsecase
import com.au.fridgly.domain.models.RecipeThumbnail
import com.au.fridgly.domain.repository.IFavoriteRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetIsFavoriteRecipeById: AbstractUsecase<Boolean, GetIsFavoriteRecipeById.Params> {
    private var favoriteRepository: IFavoriteRepository

    override fun buildUseCaseObservable(params: GetIsFavoriteRecipeById.Params): Observable<Boolean> {
        return favoriteRepository.getFavoriteById(params.context, params.id)
    }

    @Inject
    constructor(favoriteRepository: IFavoriteRepository, threadExecutor: ThreadExecutor,
                postExecutionThread: PostExecutionThread
    ): super(threadExecutor, postExecutionThread) {
        this.favoriteRepository = favoriteRepository
    }

    class Params constructor(val context: Context, val id: Int)
}
