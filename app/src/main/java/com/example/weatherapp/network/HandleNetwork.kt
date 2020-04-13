package com.example.weatherapp.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.widget.Toast
import okhttp3.Interceptor
import okhttp3.Response

class HandleNetwork(private val context: Context) : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        Log.i("getData","cek internet ${isInternetAvailable()}")
        if(!isInternetAvailable()) {
            Log.i("getData","Throw no Internet")
            throw NoInternetException()
        }

        return chain.proceed(chain.request())
    }

    private fun isInternetAvailable() :Boolean {
        val cm = context.getSystemService((Context.CONNECTIVITY_SERVICE)) as ConnectivityManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val info  = cm.activeNetwork  ?: return false
            val activeNetwork = cm.getNetworkCapabilities(info) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }
        else {
            val networkInfo = cm.activeNetworkInfo ?: return false
            return networkInfo.isConnected
        }

    }

}