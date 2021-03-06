package com.au.fridgly.presentation.views.usecases

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentActivity
import android.view.inputmethod.InputMethodManager
import com.au.fridgly.presentation.AndroidApplication
import com.au.fridgly.presentation.internal.components.ApplicationComponent
import com.au.fridgly.presentation.internal.modules.ActivityModule
import com.au.fridgly.presentation.views.helpers.ConnectionManager

abstract class BaseActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun replaceFragment(containerViewId: Int, fragment: android.support.v4.app.Fragment) {
        val fragmentTransaction = this.supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(containerViewId, fragment)
        fragmentTransaction.commit()
    }

    protected fun getActivityModule(): ActivityModule {
        return ActivityModule(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return (application as AndroidApplication).getApplicationComponent()
    }

    fun showDialog(dialogFragment: DialogFragment){
        dialogFragment.show(this.supportFragmentManager, "DialogProfileTagsAll")
        this.supportFragmentManager?.executePendingTransactions()
    }

    fun checkInternetConnection(): Boolean{
        val connectionManager = ConnectionManager(this.baseContext)
        return connectionManager.isOnline()
    }

    fun closeKeyboard(){
        if (currentFocus != null){
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus.windowToken, 0)
        }
    }
}