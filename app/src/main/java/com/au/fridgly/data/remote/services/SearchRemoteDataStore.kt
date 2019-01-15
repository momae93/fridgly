package com.au.fridgly.data.remote.services

import com.au.fridgly.data.datastore.ISearchDatastore
import com.au.fridgly.data.remote.mappers.SearchRandomRecipesMapper
import com.au.fridgly.domain.models.RecipeThumbnail
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Inject

class SearchRemoteDataStore: ISearchDatastore {

    private var service: ISearchService
    private var searchRandomRecipesMapper: SearchRandomRecipesMapper

    @Inject
    constructor(retrofit: Retrofit, searchRandomRecipesMapper: SearchRandomRecipesMapper){
        this.service = retrofit.create(ISearchService::class.java)
        this.searchRandomRecipesMapper = searchRandomRecipesMapper
    }


    override fun getRandomRecipes(key: String, number: Int): Observable<List<RecipeThumbnail>> {
        return service.getRandomRecipes(key, number).map(searchRandomRecipesMapper::transform)
    }
}