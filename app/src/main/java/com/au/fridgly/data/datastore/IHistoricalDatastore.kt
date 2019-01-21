package com.au.fridgly.data.datastore

import android.content.Context
import com.au.fridgly.data.local.models.HistoricalRecipeDB
import com.au.fridgly.domain.models.RecipeThumbnail
import io.reactivex.Observable

interface IHistoricalDatastore {
    fun getHistorical(context: Context): Observable<List<RecipeThumbnail>>
    fun insertHistorical(context: Context, historicalRecipeDB: HistoricalRecipeDB): Observable<Void>
}