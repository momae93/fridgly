package com.au.fridgly.data.remote.mappers

import com.au.fridgly.data.remote.models.SearchRecipesAPI
import com.au.fridgly.domain.models.RecipeThumbnail
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRecipesMapper @Inject constructor() {
    fun transform(elt: SearchRecipesAPI): RecipeThumbnail {
        return RecipeThumbnail(elt.id, elt.name, elt.image)
    }
}