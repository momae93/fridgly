package com.au.fridgly.domain.models

data class RecipeDetails(val id: Int,
                         val name: String,
                         val servings: Int,
                         val image: String,
                         val instructions: String?,
                         val preparation: Int?,
                         val cooking: Int?,
                         val ready: Int,
                         val ingredients: List<ExtendedIngredient>,
                         val likes: Int)