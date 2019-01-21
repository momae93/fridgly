package com.au.fridgly.data.datastore

import android.content.Context
import com.au.fridgly.data.local.models.FavoriteRecipeDB
import com.au.fridgly.domain.models.RecipeThumbnail
import io.reactivex.Observable

interface IFavoriteDatastore {
    fun getFavorites(context: Context): Observable<List<RecipeThumbnail>>
    fun getFavoriteById(context: Context, idRecipe: Int): Observable<Boolean>
    fun insertFavorites(context: Context, favoriteRecipeDB: FavoriteRecipeDB): Observable<Boolean>
}