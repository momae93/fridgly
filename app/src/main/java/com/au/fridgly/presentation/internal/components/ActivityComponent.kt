package com.au.fridgly.presentation.internal.components

import android.app.Activity
import com.au.fridgly.presentation.internal.modules.ActivityModule
import com.au.fridgly.presentation.internal.scopes.PerActivity
import dagger.Component

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun activity(): Activity
}