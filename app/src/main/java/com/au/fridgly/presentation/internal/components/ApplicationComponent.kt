package com.au.fridgly.presentation.internal.components

import android.content.Context
import com.au.fridgly.domain.executors.PostExecutionThread
import com.au.fridgly.domain.executors.ThreadExecutor
import com.au.fridgly.domain.repository.IFavoriteRepository
import com.au.fridgly.domain.repository.ISearchRepository
import com.au.fridgly.presentation.internal.modules.ApplicationModule
import com.au.fridgly.presentation.internal.modules.NetworkModule
import com.au.fridgly.presentation.views.usecases.BaseActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {
    fun inject(activity: BaseActivity)

    fun context(): Context
    fun threadExecutor(): ThreadExecutor
    fun postExecutionThread(): PostExecutionThread

    fun searchRepository(): ISearchRepository
    fun favoriteRepository(): IFavoriteRepository
}