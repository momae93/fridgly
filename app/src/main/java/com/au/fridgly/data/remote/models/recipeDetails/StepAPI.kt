package com.au.fridgly.data.remote.models.recipeDetails

import com.squareup.moshi.Json

data class StepAPI(@Json(name = "number") val number: Int,
                   @Json(name = "step") val description: String,
                   @Json(name = "ingredients") val ingredients: List<IngredientAPI>?,
                   @Json(name = "equipment") val equipments: List<EquipmentAPI>?)