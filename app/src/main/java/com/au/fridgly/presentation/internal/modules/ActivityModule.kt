package com.au.fridgly.presentation.internal.modules

import android.app.Activity
import com.au.fridgly.presentation.internal.scopes.PerActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule (val activity: Activity) {

    @Provides
    @PerActivity
    fun activity(): Activity {
        return this.activity
    }
}