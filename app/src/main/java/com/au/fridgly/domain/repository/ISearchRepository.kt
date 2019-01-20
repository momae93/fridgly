package com.au.fridgly.domain.repository

import com.au.fridgly.domain.models.recipeDetails.RecipeDetails
import com.au.fridgly.domain.models.RecipeThumbnail
import io.reactivex.Observable

interface ISearchRepository {
    fun getRandomRecipe(key: String, number: Int): Observable<List<RecipeThumbnail>>
    fun getSearchRecipe(key: String, number: Int, ingredients: String): Observable<List<RecipeThumbnail>>
    fun getRecipeDetailsById(key: String, idRecipe: Int): Observable<RecipeDetails>
}