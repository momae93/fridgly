package com.au.fridgly.data.datastore

import com.au.fridgly.domain.models.RecipeThumbnail
import io.reactivex.Observable


interface ISearchDatastore {
    fun getRandomRecipes(key: String, number: Int): Observable<List<RecipeThumbnail>>
}