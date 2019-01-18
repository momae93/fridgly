package com.au.fridgly.data.remote.services

import com.au.fridgly.data.datastore.ISearchDatastore
import com.au.fridgly.data.remote.mappers.RecipeDetailsMapper
import com.au.fridgly.data.remote.mappers.SearchRandomRecipesMapper
import com.au.fridgly.data.remote.mappers.SearchRecipesMapper
import com.au.fridgly.domain.models.RecipeDetails
import com.au.fridgly.domain.models.RecipeThumbnail
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Inject

class SearchRemoteDataStore: ISearchDatastore {

    private var service: ISearchService
    private var searchRandomRecipesMapper: SearchRandomRecipesMapper
    private var searchRecipesMapper: SearchRecipesMapper
    private var recipeDetailsMapper: RecipeDetailsMapper

    @Inject
    constructor(retrofit: Retrofit, searchRandomRecipesMapper: SearchRandomRecipesMapper,
                searchRecipesMapper: SearchRecipesMapper, recipeDetailsMapper: RecipeDetailsMapper){
        this.service = retrofit.create(ISearchService::class.java)
        this.searchRandomRecipesMapper = searchRandomRecipesMapper
        this.searchRecipesMapper = searchRecipesMapper
        this.recipeDetailsMapper = recipeDetailsMapper
    }

    override fun getRandomRecipes(key: String, number: Int): Observable<List<RecipeThumbnail>> {
        return service.getRandomRecipes(key, number).map(searchRandomRecipesMapper::transform)
    }

    override fun getSearchRecipes(key: String, number: Int, ingredients: String): Observable<List<RecipeThumbnail>> {
        return service.getSearchRecipes(key, number, ingredients).map{it.map(searchRecipesMapper::transform)}
    }

    override fun getRecipeDetailsById(key: String, idRecipe: Int): Observable<RecipeDetails> {
        return service.getRecipeDetailsById(key, idRecipe).map(recipeDetailsMapper::transform)
    }
}