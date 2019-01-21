package com.au.fridgly.data.repository

import android.content.Context
import com.au.fridgly.data.local.mappers.FavoriteRecipeMapper
import com.au.fridgly.data.local.services.FavoriteLocalDatastore
import com.au.fridgly.domain.models.RecipeThumbnail
import com.au.fridgly.domain.repository.IFavoriteRepository
import io.reactivex.Observable
import javax.inject.Inject

class FavoriteDataRepository @Inject constructor(): IFavoriteRepository{

    @Inject
    lateinit var localDataStore: FavoriteLocalDatastore

    override fun getFavorites(context: Context): Observable<List<RecipeThumbnail>> {
        return localDataStore.getFavorites(context)
    }

    override fun insertFavorite(context: Context, recipeThumbnail: RecipeThumbnail): Observable<Boolean>{
        val favoriteRecipeDB = FavoriteRecipeMapper().transform(recipeThumbnail)
        return localDataStore.insertFavorites(context, favoriteRecipeDB)
    }

    override fun getFavoriteById(context: Context, idRecipe: Int): Observable<Boolean> {
        return localDataStore.getFavoriteById(context, idRecipe)
    }
}