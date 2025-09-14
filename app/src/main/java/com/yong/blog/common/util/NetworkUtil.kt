package com.yong.blog.common.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object NetworkUtil {
    var connManager: ConnectivityManager? = null

    fun isNetworkAvail(context: Context): Boolean {
        if(connManager == null) {
            connManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        }

        val activeNetwork = connManager?.activeNetwork ?: return false
        val isActiveNetwork = connManager?.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            isActiveNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            isActiveNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }
}