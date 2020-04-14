package com.example.weatherapp

import android.app.Application
import android.util.Log
import com.example.weatherapp.di.AppComponent
import com.example.weatherapp.di.DaggerAppComponent
import com.example.weatherapp.di.NetworkModule

class App : Application() {
    lateinit var appComponent : AppComponent

    companion object {
        lateinit var app : App
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        Log.i("destroy",this.hashCode().toString())
        appComponent = DaggerAppComponent.builder().getApp(this).build()

    }

    fun getApplicationComponent() : AppComponent {
        return appComponent
    }
}