package com.au.fridgly.data.remote.models.randomRecipe

import com.squareup.moshi.Json

data class RandomRecipesAPI(@Json(name = "recipes") val list: List<RecipeThumbnailAPI>)