package com.au.fridgly.domain.models.recipeDetails

data class ExtendedIngredient(val id: Int?,
                              val name: String,
                              val image: String?,
                              val amount: Float,
                              val unit: String)
