package com.au.fridgly.data.local.services

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.au.fridgly.data.local.models.HistoricalRecipeDB

@Dao
interface IHistoricalService {
    @Insert
    fun insert(vararg recipe: HistoricalRecipeDB)

    @Query("SELECT * FROM t_historical")
    fun getAll(): List<HistoricalRecipeDB>
}