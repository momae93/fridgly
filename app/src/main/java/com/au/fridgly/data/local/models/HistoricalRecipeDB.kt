package com.au.fridgly.data.local.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "t_historical")
data class HistoricalRecipeDB (
    @ColumnInfo(name = "idRecipe") var id: Int,
    @ColumnInfo(name = "title") var name: String,
    @ColumnInfo(name = "image") var image: String,
    @ColumnInfo(name = "date") var date: Long
)
{
    @PrimaryKey(autoGenerate = true) var historical_id: Int = 0
}