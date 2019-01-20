package com.au.fridgly.domain.models.recipeDetails

data class RecipeDetails(val id: Int,
                         val name: String,
                         val servings: Int,
                         val image: String,
                         val instructions: String?,
                         val preparation: Int?,
                         val cooking: Int?,
                         val ready: Int,
                         val ingredients: List<ExtendedIngredient>,
                         val steps: List<Step>?,
                         val likes: Int)