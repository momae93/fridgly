package com.au.fridgly.data.remote.models.recipeDetails

import com.squareup.moshi.Json

data class TemperatureAPI(@Json(name = "number") val temperature: Int,
                          @Json(name = "unit") val unit: String)