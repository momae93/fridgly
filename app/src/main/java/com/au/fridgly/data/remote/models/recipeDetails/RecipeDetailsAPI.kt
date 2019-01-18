package com.au.fridgly.data.remote.models.recipeDetails

import com.squareup.moshi.Json

data class RecipeDetailsAPI(@Json(name = "id") val id: Int,
                            @Json(name = "title") val name: String,
                            @Json(name = "servings") val servings: Int,
                            @Json(name = "image") val image: String,
                            @Json(name = "instructions") val instructions: String?,
                            @Json(name = "preparationMinutes") val preparation: Int?,
                            @Json(name = "cookingMinutes") val cooking: Int?,
                            @Json(name = "readyInMinutes") val ready: Int,
                            @Json(name = "aggregateLikes") val likes: Int,
                            @Json(name = "extendedIngredients") val ingredients: List<ExtendedIngredientAPI>)

