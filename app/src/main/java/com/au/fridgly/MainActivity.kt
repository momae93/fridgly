package com.au.fridgly

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_trophy -> {
                message.setText(R.string.title_trophy)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_search -> {
                message.setText(R.string.title_search)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorite -> {
                message.setText(R.string.title_favorite)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
