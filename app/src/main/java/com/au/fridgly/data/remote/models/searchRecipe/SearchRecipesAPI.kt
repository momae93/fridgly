package com.au.fridgly.data.remote.models.searchRecipe

import com.squareup.moshi.Json

data class SearchRecipesAPI(@Json(name = "id") val id: Int,
                            @Json(name = "title") val name: String,
                            @Json(name = "image") val image: String,
                            @Json(name = "likes") val likes: Int)