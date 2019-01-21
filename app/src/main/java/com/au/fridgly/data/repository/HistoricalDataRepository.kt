package com.au.fridgly.data.repository

import android.content.Context
import com.au.fridgly.data.local.mappers.HistoricalRecipeMapper
import com.au.fridgly.data.local.services.HistoricalLocalDatastore
import com.au.fridgly.domain.models.RecipeThumbnail
import com.au.fridgly.domain.repository.IHistoricalRepository
import io.reactivex.Observable
import javax.inject.Inject

class HistoricalDataRepository @Inject constructor(): IHistoricalRepository {

    @Inject
    lateinit var localDataStore: HistoricalLocalDatastore

    override fun getHistorical(context: Context): Observable<List<RecipeThumbnail>> {
        return localDataStore.getHistorical(context)
    }

    override fun insertHistorical(context: Context, recipeThumbnail: RecipeThumbnail): Observable<Void> {
        val historicalDB = HistoricalRecipeMapper().transform(recipeThumbnail)
        return localDataStore.insertHistorical(context, historicalDB)
    }
}