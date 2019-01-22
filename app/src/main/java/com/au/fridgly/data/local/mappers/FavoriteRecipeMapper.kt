package com.au.fridgly.data.local.mappers

import com.au.fridgly.data.local.models.FavoriteRecipeDB
import com.au.fridgly.domain.models.RecipeThumbnail
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteRecipeMapper @Inject constructor() {
    fun transform(elt: FavoriteRecipeDB): RecipeThumbnail {
        return RecipeThumbnail(elt.id, elt.name, elt.image)
    }

    fun transform(elt: RecipeThumbnail): FavoriteRecipeDB {
        return FavoriteRecipeDB(elt.id, elt.name, elt.image ?: "")
    }
}
