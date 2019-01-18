package com.au.fridgly.data.datastore

import com.au.fridgly.domain.models.RecipeDetails
import com.au.fridgly.domain.models.RecipeThumbnail
import io.reactivex.Observable


interface ISearchDatastore {
    fun getRandomRecipes(key: String, number: Int): Observable<List<RecipeThumbnail>>
    fun getSearchRecipes(key: String, number: Int, ingredients: String): Observable<List<RecipeThumbnail>>
    fun getRecipeDetailsById(key: String, idRecipe: Int): Observable<RecipeDetails>
}