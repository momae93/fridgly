package com.au.fridgly.data.remote.models.recipeDetails

import com.squareup.moshi.Json

data class ExtendedIngredientAPI(@Json(name = "id") val id: Int,
                                 @Json(name = "name") val name: String,
                                 @Json(name = "image") val image: String,
                                 @Json(name = "original") val description: String,
                                 @Json(name = "amount") val amount: Float,
                                 @Json(name = "unit") val unit: String)
