package com.au.fridgly.data.local.services

import android.content.Context
import com.au.fridgly.data.datastore.IHistoricalDatastore
import com.au.fridgly.data.local.AbstractDatabase
import com.au.fridgly.data.local.mappers.FavoriteRecipeMapper
import com.au.fridgly.data.local.mappers.HistoricalRecipeMapper
import com.au.fridgly.data.local.models.HistoricalRecipeDB
import com.au.fridgly.domain.models.RecipeThumbnail
import io.reactivex.Observable
import javax.inject.Inject

class HistoricalLocalDatastore: IHistoricalDatastore {

    private var favoriteRecipeMapper: FavoriteRecipeMapper
    private var historicalRecipeMapper: HistoricalRecipeMapper

    @Inject
    constructor(favoriteRecipeMapper: FavoriteRecipeMapper,
                historicalRecipeMapper: HistoricalRecipeMapper){
        this.favoriteRecipeMapper = favoriteRecipeMapper
        this.historicalRecipeMapper = historicalRecipeMapper
    }

    override fun getHistorical(context: Context): Observable<List<RecipeThumbnail>> {
        val list = AbstractDatabase.getDatabase(context)
            .historicalService()
            .getAll()
            .reversed()
            .map(historicalRecipeMapper::transform)

        return Observable.just(list)
    }

    override fun insertHistorical(context: Context, historicalRecipeDB: HistoricalRecipeDB): Observable<Void> {
        AbstractDatabase
            .getDatabase(context)
            .historicalService()
            .insert(historicalRecipeDB)
        return Observable.empty()
    }
}