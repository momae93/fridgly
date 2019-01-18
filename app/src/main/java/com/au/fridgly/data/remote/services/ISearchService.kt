package com.au.fridgly.data.remote.services

import com.au.fridgly.data.remote.models.randomRecipe.RandomRecipesAPI
import com.au.fridgly.data.remote.models.recipeDetails.RecipeDetailsAPI
import com.au.fridgly.data.remote.models.searchRecipe.SearchRecipesAPI
import io.reactivex.Observable
import retrofit2.http.*

interface ISearchService {
    @GET("/recipes/random?")
    fun getRandomRecipes(@Header("X-RapidAPI-Key") key: String,
                         @Query("number") number: Int): Observable<RandomRecipesAPI>

    @GET("/recipes/findByIngredients?")
    fun getSearchRecipes(@Header("X-RapidAPI-Key") key: String,
                         @Query("number") number: Int,
                         @Query("ingredients") ingredients: String): Observable<List<SearchRecipesAPI>>

    @GET("recipes/{idRecipe}/information")
    fun getRecipeDetailsById(@Header("X-RapidAPI-Key") key: String,
                  @Path("idRecipe") idRecipe: Int): Observable<RecipeDetailsAPI>
}