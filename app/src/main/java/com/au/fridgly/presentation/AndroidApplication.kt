package com.au.fridgly.presentation

import android.app.Application
import com.au.fridgly.presentation.internal.components.ApplicationComponent
import com.au.fridgly.presentation.internal.components.DaggerApplicationComponent
import com.au.fridgly.presentation.internal.modules.ApplicationModule
import com.au.fridgly.presentation.internal.modules.NetworkModule

class AndroidApplication : Application() {

    private lateinit var applicationComponent: ApplicationComponent
    private val BASE_URL = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com"

    @Override
    override fun onCreate() {
        super.onCreate()
        initializeInjector()
    }

    private fun initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent
            .builder()
            .networkModule(NetworkModule(BASE_URL))
            .applicationModule(ApplicationModule(this))
            .build()
    }

    fun getApplicationComponent(): ApplicationComponent {
        return this.applicationComponent
    }
}