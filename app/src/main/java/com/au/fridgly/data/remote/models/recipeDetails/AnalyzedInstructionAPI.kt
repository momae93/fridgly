package com.au.fridgly.data.remote.models.recipeDetails

import com.squareup.moshi.Json

data class AnalyzedInstructionAPI(@Json(name = "name") val name: String,
                                  @Json(name = "steps") val steps: List<StepAPI>)