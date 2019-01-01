package com.au.fridgly.presentation.views.usecases

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import com.au.fridgly.R
import com.au.fridgly.presentation.internal.components.DaggerMainActivityComponent
import com.au.fridgly.presentation.internal.components.MainActivityComponent
import com.au.fridgly.presentation.views.usecases.search.FragmentSearch
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var component: MainActivityComponent

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_search -> {
                replaceFragment(R.id.activity_main_framelayout_content, FragmentSearch())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_trophy -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorite -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeInjection()
        replaceFragment(R.id.activity_main_framelayout_content, FragmentSearch())
        activity_main_bottomNavigationView_nav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun initializeInjection(){
        component = DaggerMainActivityComponent.builder()
            .activityModule(getActivityModule())
            .applicationComponent(getApplicationComponent())
            .build()
    }

    fun getComponent(): MainActivityComponent {
        return component
    }

}
