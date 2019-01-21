package com.au.fridgly.data.local.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "t_favorite")
data class FavoriteRecipeDB (
    @ColumnInfo(name = "idRecipe") var id: Int,
    @ColumnInfo(name = "title") var name: String,
    @ColumnInfo(name = "image") var image: String)
{
    @PrimaryKey(autoGenerate = true) var favorite_id: Int = 0
}