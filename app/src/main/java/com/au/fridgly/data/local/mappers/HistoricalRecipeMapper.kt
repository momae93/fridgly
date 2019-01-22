package com.au.fridgly.data.local.mappers

import com.au.fridgly.data.local.models.HistoricalRecipeDB
import com.au.fridgly.domain.models.RecipeThumbnail
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HistoricalRecipeMapper @Inject constructor() {
    fun transform(elt: HistoricalRecipeDB): RecipeThumbnail {
        return RecipeThumbnail(elt.id, elt.name, elt.image)
    }

    fun transform(elt: RecipeThumbnail): HistoricalRecipeDB {
        return HistoricalRecipeDB(id = elt.id, name = elt.name, image = elt.image ?: "", date = Date().time)
    }
}
