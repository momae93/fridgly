package com.au.fridgly.presentation.views.usecases

import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.inputmethod.InputMethodManager
import com.au.fridgly.presentation.AndroidApplication
import com.au.fridgly.presentation.internal.components.ApplicationComponent
import com.au.fridgly.presentation.internal.modules.ActivityModule

abstract class BaseActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //this.getApplicationComponent().inject(this)
    }

    fun replaceFragment(containerViewId: Int, fragment: android.support.v4.app.Fragment) {
        val fragmentTransaction = this.supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(containerViewId, fragment)
        fragmentTransaction.commit()
    }

    fun addFragment(containerViewId: Int, fragment: android.support.v4.app.Fragment) {
        val fragmentTransaction = this.supportFragmentManager.beginTransaction()
        fragmentTransaction.add(containerViewId, fragment)
        fragmentTransaction.commit()
    }


    protected fun getActivityModule(): ActivityModule {
        return ActivityModule(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return (application as AndroidApplication).getApplicationComponent()
    }

    fun closeKeyboard(){
        if (currentFocus != null){
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus.windowToken, 0)
        }
    }
}