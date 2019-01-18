package com.au.fridgly.data.remote.models.randomRecipe

import com.squareup.moshi.Json

data class RecipeThumbnailAPI(@Json(name = "id") val id: Int,
                              @Json(name = "title") val name: String,
                              @Json(name = "readyInMinutes") val time: Int,
                              @Json(name = "servings") val servings: Int,
                              @Json(name = "image") val image: String)