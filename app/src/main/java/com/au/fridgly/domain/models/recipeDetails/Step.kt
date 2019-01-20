package com.au.fridgly.domain.models.recipeDetails

data class Step(val number: Int,
                val description: String,
                val ingredients: List<Ingredient>?,
                val equipments: List<Equipment>?)
