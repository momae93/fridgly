package com.au.fridgly.presentation.views.helpers

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.support.annotation.RequiresApi

class ConnectionManager(val context: Context) {
    @RequiresApi(Build.VERSION_CODES.M)
    fun isOnline(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (connectivityManager != null && connectivityManager.activeNetwork != null){
            val activeNetwork: NetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetwork.isConnected
        }
        return false
    }
}