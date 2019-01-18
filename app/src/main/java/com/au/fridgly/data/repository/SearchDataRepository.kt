package com.au.fridgly.data.repository

import com.au.fridgly.data.remote.services.SearchRemoteDataStore
import com.au.fridgly.domain.models.RecipeDetails
import com.au.fridgly.domain.models.RecipeThumbnail
import com.au.fridgly.domain.repository.ISearchRepository
import io.reactivex.Observable
import javax.inject.Inject

class SearchDataRepository @Inject constructor(): ISearchRepository {

    @Inject
    lateinit var remoteDataStore: SearchRemoteDataStore

    override fun getRandomRecipe(key: String, number: Int): Observable<List<RecipeThumbnail>> {
        return remoteDataStore.getRandomRecipes(key, number)
    }

    override fun getSearchRecipe(key: String, number: Int, ingredients: String): Observable<List<RecipeThumbnail>> {
        return remoteDataStore.getSearchRecipes(key, number, ingredients)
    }

    override fun getRecipeDetailsById(key: String, idRecipe: Int): Observable<RecipeDetails> {
        return remoteDataStore.getRecipeDetailsById(key, idRecipe)
    }
}