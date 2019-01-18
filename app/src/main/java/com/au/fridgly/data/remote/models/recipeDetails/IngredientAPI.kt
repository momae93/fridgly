package com.au.fridgly.data.remote.models.recipeDetails

import com.squareup.moshi.Json

data class IngredientAPI(@Json(name = "id") val id: Int,
                         @Json(name = "name") val name: String,
                         @Json(name = "image") val image: String)