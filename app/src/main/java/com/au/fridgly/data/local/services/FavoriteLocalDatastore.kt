package com.au.fridgly.data.local.services

import android.content.Context
import com.au.fridgly.data.datastore.IFavoriteDatastore
import com.au.fridgly.data.local.AbstractDatabase
import com.au.fridgly.data.local.mappers.FavoriteRecipeMapper
import com.au.fridgly.data.local.models.FavoriteRecipeDB
import com.au.fridgly.domain.models.RecipeThumbnail
import io.reactivex.Observable
import javax.inject.Inject

class FavoriteLocalDatastore: IFavoriteDatastore {

    private var favoriteRecipeMapper: FavoriteRecipeMapper

    @Inject
    constructor(favoriteRecipeMapper: FavoriteRecipeMapper){
        this.favoriteRecipeMapper = favoriteRecipeMapper
    }

    override fun getFavorites(context: Context): Observable<List<RecipeThumbnail>> {
        val list = AbstractDatabase.getDatabase(context)
            .favoriteService()
            .getAll()
            .map(favoriteRecipeMapper::transform)

        return Observable.just(list)
    }

    override fun getFavoriteById(context: Context, idRecipe: Int): Observable<Boolean> {
        val recipe: FavoriteRecipeDB? = AbstractDatabase
            .getDatabase(context)
            .favoriteService()
            .getFavoriteById(idRecipe)
        val isFavorite = recipe != null

        return Observable.just(isFavorite)
    }

    override fun insertFavorites(context: Context, favoriteRecipeDB: FavoriteRecipeDB): Observable<Boolean> {
        val recipe: FavoriteRecipeDB? = AbstractDatabase
            .getDatabase(context)
            .favoriteService()
            .getFavoriteById(favoriteRecipeDB.id)

        if (recipe != null)
            AbstractDatabase.getDatabase(context).favoriteService().delete(favoriteRecipeDB.id)
        else
            AbstractDatabase.getDatabase(context).favoriteService().insert(favoriteRecipeDB)
        return Observable.just(recipe == null)
    }
}