package com.au.fridgly.data.local.services

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.au.fridgly.data.local.models.FavoriteRecipeDB

@Dao
interface IFavoriteService {
    @Query("SELECT * FROM t_favorite")
    fun getAll(): List<FavoriteRecipeDB>

    @Query("SELECT * FROM t_favorite WHERE t_favorite.idRecipe == :idRecipe")
    fun getFavoriteById(idRecipe: Int): FavoriteRecipeDB

    @Query("DELETE FROM t_favorite WHERE t_favorite.idRecipe == :idRecipe")
    fun delete(idRecipe: Int)

    @Insert
    fun insert(vararg person: FavoriteRecipeDB)
}