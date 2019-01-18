package com.au.fridgly.presentation.internal.components

import com.au.fridgly.presentation.internal.modules.ActivityModule
import com.au.fridgly.presentation.internal.scopes.PerActivity
import com.au.fridgly.presentation.views.usecases.recipe.fragment.DialogFragmentRecipe
import com.au.fridgly.presentation.views.usecases.search.fragment.FragmentSearch
import com.au.fridgly.presentation.views.usecases.search.fragment.FragmentSearchRandom
import com.au.fridgly.presentation.views.usecases.search.fragment.FragmentSearchResults
import dagger.Component

@PerActivity
@Component(dependencies = [(ApplicationComponent::class)], modules = [ActivityModule::class])
interface MainActivityComponent : ActivityComponent{
    fun inject(fragmentSearch: FragmentSearch)
    fun inject(fragmentSearchRandom: FragmentSearchRandom)
    fun inject(fragmentSearchResults: FragmentSearchResults)

    fun inject(dialogFragmentRecipe: DialogFragmentRecipe)
}