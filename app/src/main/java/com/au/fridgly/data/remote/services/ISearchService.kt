package com.au.fridgly.data.remote.services

import com.au.fridgly.data.remote.models.RandomRecipesAPI
import com.au.fridgly.data.remote.models.SearchRecipesAPI
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
}