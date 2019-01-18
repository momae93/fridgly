package com.au.fridgly.data.remote.models.recipeDetails

import com.squareup.moshi.Json

data class StepAPI(@Json(name = "number") val id: Int,
                   @Json(name = "step") val step: String,
                   @Json(name = "ingredients") val ingredients: List<IngredientAPI>,
                   @Json(name = "equipment") val equipments: String)