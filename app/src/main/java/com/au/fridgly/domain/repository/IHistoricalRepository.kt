package com.au.fridgly.domain.repository

import android.content.Context
import com.au.fridgly.domain.models.RecipeThumbnail
import io.reactivex.Observable

interface IHistoricalRepository {
    fun getHistorical(context: Context): Observable<List<RecipeThumbnail>>
    fun insertHistorical(context: Context, recipeThumbnail: RecipeThumbnail): Observable<Void>
}