package com.au.fridgly.presentation.internal.components

import com.au.fridgly.presentation.internal.modules.ActivityModule
import com.au.fridgly.presentation.internal.scopes.PerActivity
import com.au.fridgly.presentation.views.usecases.search.FragmentSearch
import dagger.Component

@PerActivity
@Component(dependencies = [(ApplicationComponent::class)], modules = [ActivityModule::class])
interface MainActivityComponent : ActivityComponent{
    fun inject(fragmentProfile: FragmentSearch)
}