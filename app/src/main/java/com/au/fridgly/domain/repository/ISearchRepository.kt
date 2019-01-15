package com.au.fridgly.domain.repository

import com.au.fridgly.domain.models.RecipeThumbnail
import io.reactivex.Observable

interface ISearchRepository {
    fun getRandomRecipe(key: String, number: Int): Observable<List<RecipeThumbnail>>
}