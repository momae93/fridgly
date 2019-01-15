package com.au.fridgly.presentation.internal.modules

import android.content.Context
import com.au.fridgly.data.repository.SearchDataRepository
import com.au.fridgly.domain.executors.PostExecutionThread
import com.au.fridgly.domain.executors.ThreadExecutor
import com.au.fridgly.domain.repository.ISearchRepository
import com.au.fridgly.presentation.AndroidApplication
import com.au.fridgly.presentation.models.JobExecutor
import com.au.fridgly.presentation.models.UIThread
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(val application: AndroidApplication) {

    @Provides
    @Singleton
    fun application(): Context {
        return this.application
    }

    @Provides
    @Singleton
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @Singleton
    fun provideSearchRepository(searchDataRepository: SearchDataRepository): ISearchRepository {
        return searchDataRepository
    }
}