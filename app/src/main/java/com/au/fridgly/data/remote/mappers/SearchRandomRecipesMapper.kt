package com.au.fridgly.data.remote.mappers

import com.au.fridgly.data.remote.models.randomRecipe.RandomRecipesAPI
import com.au.fridgly.domain.models.RecipeThumbnail
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRandomRecipesMapper @Inject constructor() {
    fun transform(elt: RandomRecipesAPI): List<RecipeThumbnail> {
        return elt.list.map {
            RecipeThumbnail(id = it.id, name = it.name, image = it.image)
        }
    }
}