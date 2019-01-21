package com.au.fridgly.domain.repository

import android.content.Context
import com.au.fridgly.domain.models.RecipeThumbnail
import io.reactivex.Observable

interface IFavoriteRepository {
    fun getFavorites(context: Context): Observable<List<RecipeThumbnail>>
    fun getFavoriteById(context: Context, idRecipe: Int): Observable<Boolean>
    fun insertFavorite(context: Context, recipeThumbnail: RecipeThumbnail): Observable<Boolean>
}